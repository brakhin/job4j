package ru.bgbrakhi.sql.jobparser;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SqlRuParser {
    public static String appProperties;

    public SqlRuParser() {
        initSheduler();
    }

    private void initSheduler() {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();

        try (InputStream in = SqlRuParser.class.getClassLoader().getResourceAsStream(appProperties)) {
            Properties config = new Properties();
            config.load(in);

            Scheduler sheduler = schedulerFactory.getScheduler();

            JobDetail job = JobBuilder.newJob(SimpleJob.class)
                    .withIdentity("myJob", "group1")
                    .build();

            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger3", "group1")
                    .withSchedule(CronScheduleBuilder.cronSchedule(config.getProperty("cron.time")))
                    .forJob("myJob", "group1")
                    .build();

            sheduler.scheduleJob(job, trigger);
            sheduler.start();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            appProperties = "app.properties";
        } else {
            appProperties = args[0];
        }
        SqlRuParser sqlRuParser = new SqlRuParser();
    }
}
