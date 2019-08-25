package ru.bgbrakhi.sql.jobparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SqlRuParser {
    private static final Logger LOG = LogManager.getLogger(SqlRuParser.class);

    public SqlRuParser() {
        initSheduler();
    }

    private void initSheduler() {
        Properties properties = new Properties();
        properties.setProperty("org.quartz.threadPool.threadCount", String.valueOf(1));

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();

        try (InputStream in = SqlRuParser.class.getClassLoader().getResourceAsStream("app.properties")) {
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
            LOG.error(e.getMessage(), e);
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
