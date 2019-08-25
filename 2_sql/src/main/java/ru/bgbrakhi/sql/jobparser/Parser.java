package ru.bgbrakhi.sql.jobparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Parser {
    private static final Logger LOG = LogManager.getLogger(Parser.class);

    public List<Vacancy> parse(long lastTime, String page) {
        List<Vacancy> result = new ArrayList<>();
        long vacancyTime = 0;
        try {
            Document doc = Jsoup.connect(page).get();
            Elements tables = doc.select("table");
            Element forumTable = tables.stream().filter(e ->
                    "forumTable".equals(e.attributes().get("class"))).collect(Collectors.toList()).get(0);

            for (Element row : forumTable.select("tr")) {
                Elements cols = row.select("td");
                if (cols.size() == 6) {
                    String name = cols.get(1).text();
                    String link = cols.get(1).select("a[href]").get(0).attr("abs:href");
                    String time = cols.get(5).text();
                    vacancyTime = Utils.convertStrTime2Milliseconds(time);
                    if (Utils.validVacancy(lastTime, vacancyTime, name)) {
                        String text = null;
                        try {
                            text = Utils.getText(link);
                        } catch (IOException e) {
                            LOG.error(e.getMessage(), e);
                        }
                        result.add(new Vacancy(name, text, link, vacancyTime));
                    }
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public static void main(String[] args) {
        Parser parser = new Parser();
        List<Vacancy> list = parser.parse(0, "https://www.sql.ru/forum/job/1");
    }
}
