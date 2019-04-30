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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.stream.Collectors;

public class Parser implements Closeable {
    private static final Logger LOG = LogManager.getLogger(Parser.class.getName());

    private String configName;
    private long vacancyTime;
    private long lastTime;
    private int added = 0;

    private Connection connection;
    private Properties config;

    public Parser(String configName)  {
        this.configName = configName;
        this.lastTime = Math.max(getLastTime(), getFirtYearDayTime());
        initConfig();
    }

    private long getFirtYearDayTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis();
    }

    private long convertStrTime2Milliseconds(String value) {
        String[] arr = value.split(",");
        String[] time = arr[1].split(":");

        Calendar calendar = Calendar.getInstance();

        switch (arr[0]) {
            case "вчера" :
                calendar.add(Calendar.DATE, -1);
                break;
            case "сегодня" :
                break;
            default:
                String[] date = arr[0].split(" ");
                calendar.set(Calendar.YEAR, 2000 + Integer.parseInt(date[2].trim()));
                calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[0].trim()));
                calendar.set(Calendar.MONTH,
                        Arrays.asList("янв", "фев", "мар", "апр", "май", "июн",
                                      "июл", "авг", "сен", "окт", "ноя", "дек").indexOf(date[1].trim())
                );
        }
        calendar.set(Calendar.HOUR, Integer.parseInt(time[0].trim()));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0].trim()));
        calendar.set(Calendar.MINUTE, Integer.parseInt(time[1].trim()));
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis();
    }


    private void storeLastTime() {
        lastTime = System.currentTimeMillis();
        Properties properties = new Properties();
        properties.put("lastTime", Long.toString(lastTime));
        try {
            properties.store(new FileWriter("app.settings"), "lastTime");
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    private long getLastTime() {
        long result = 0;
        Properties properties = new Properties();
        try (FileReader reader = new FileReader("app.settings")) {
            properties.load(reader);
            result = Long.parseLong((String) properties.get("lastTime"));
        } catch (IOException e) {
            LOG.error(e.getMessage());
        } catch (NumberFormatException e) {
            LOG.error(e.getMessage());
        }
        return result;
    }

    private void initConfig() {
        try (InputStream is = Parser.class.getClassLoader().getResourceAsStream(configName)) {
            config = new Properties();
            config.load(is);
            Class.forName(config.getProperty("jdbc.driver"));
            connection = DriverManager.getConnection(
                    config.getProperty("jdbc.url"),
                    config.getProperty("jdbc.username"),
                    config.getProperty("jdbc.password")
            );
        } catch (ClassNotFoundException e) {
            LOG.error(e.getMessage());
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parse() {
        LOG.info("Start parcing");
        int index = 1;
        boolean validLink = true;
        do {
            try {
                parseLink(String.format("https://www.sql.ru/forum/job/%d", index++));
            } catch (IOException e) {
                LOG.error(e.getMessage());
                validLink = false;
            }
        } while (validLink && vacancyTime > lastTime);
        storeLastTime();
        LOG.info(String.format("Stop parcing. Set lastTime to %s, %d records was added", new Date(lastTime).toString(), added));
        LOG.info("-----------------------------------------------------------------------------------------------");
    }

    private void parseLink(String link) throws IOException {
        Document doc = Jsoup.connect(link).get();
        Elements tables = doc.select("table");
        Element forumTable = tables.stream().filter(e -> "forumTable".equals(e.attributes().get("class"))).collect(Collectors.toList()).get(0);
        forumTable.select("tr").forEach(this::parseRow);
    }

    private void parseRow(Element row) {
        Elements cols = row.select("td");
        if (cols.size() == 6) {
            String name = cols.get(1).text();
            String link = cols.get(1).select("a[href]").get(0).attr("abs:href");
            String time = cols.get(5).text();
            vacancyTime = convertStrTime2Milliseconds(time);
            if (validVacancy(vacancyTime, name)) {
                String text = getText(link);
                try (PreparedStatement stat = connection.prepareStatement(
                        "insert into vacancy(name, text, link) \n"
                                +
                             "values(?, ?, ?);")
                ) {
                    stat.setString(1, name);
                    stat.setString(2, text);
                    stat.setString(3, link);
                    stat.executeUpdate();
                    added++;
                } catch (SQLException e) {
                    LOG.error(e.getMessage());
                }
            }
        }
    }

    private boolean validVacancy(long time, String name) {
        return new Date(time).after(new Date(lastTime)) && name.toUpperCase().indexOf("JAVA") != -1;
    }

    private String getText(String link) {
        String result = null;
        try {
            Document doc = Jsoup.connect(link).get();
            Elements tables = doc.select("table");
            Element msgTable = tables.stream().filter(e -> "msgTable".equals(e.attributes().get("class"))).collect(Collectors.toList()).get(0);
            result = msgTable.select("tr").get(1).select("td").get(1).text();
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return result;
    }

    @Override
    public void close() throws IOException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage());
            }
        }
    }


}
