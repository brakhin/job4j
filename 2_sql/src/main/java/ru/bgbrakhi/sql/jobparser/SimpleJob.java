package ru.bgbrakhi.sql.jobparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleJob implements Job {
    private static final Logger LOG = LogManager.getLogger(SimpleJob.class);

    private final ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private final Storage storage = Storage.getInstance();
    private final Properties config = new Properties();
    private final Parser parser = new Parser();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            try (InputStream is = SimpleJob.class.getClassLoader().getResourceAsStream("app.properties")) {
                config.load(is);
                Integer pagecount = Integer.parseInt(config.getProperty("pagecount"));
                long lastTime = storage.getLastTime() + 1;
                for (int i = 1; i <= pagecount; i++) {
                    int finalI = i;
                    pool.submit(new Runnable() {
                        @Override
                        public void run() {
                            List<Vacancy> list = parser.parse(lastTime,
                                    String.format("https://www.sql.ru/forum/job/%s", finalI));
                            list.forEach(storage::saveData);
                        }
                    });
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
