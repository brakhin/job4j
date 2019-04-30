package ru.bgbrakhi.sql.jobparser;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SimpleJob implements Job {

    private Parser parser;

    public SimpleJob() {
        parser = new Parser(SqlRuParser.appProperties);
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        parser.parse();
    }
}
