package ru.bgbrakhi.sql.jobparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Utils {

    public static long convertStrTime2Milliseconds(String value) {
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

    public static boolean validVacancy(long lastTime, long time, String name) {
        return new Date(time).after(new Date(lastTime)) && name.toUpperCase().indexOf("JAVA") != -1;
    }

    public static String getText(String link) throws IOException {
        String result = null;
        Document doc = Jsoup.connect(link).get();
        Elements tables = doc.select("table");
        List<Element> msgTables = tables.stream().filter(e -> "msgTable".equals(e.attributes().get("class")))
                .collect(Collectors.toList());
        if (msgTables.size() > 0) {
            Element msgTable = msgTables.get(0);
            result = msgTable.select("tr").get(1).select("td").get(1).text();
        } else {
            result = "";
        }
        return result;
    }
}
