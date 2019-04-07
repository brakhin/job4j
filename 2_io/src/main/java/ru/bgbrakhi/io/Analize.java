package ru.bgbrakhi.io;

import java.io.*;

public class Analize {
    private String source;
    private String target;
    private String inactivityStart = "";
    private String inactivityStop = "";

    public Analize(String source, String target) {
        this.source = source;
        this.target = target;
    }

    private void processLine(String line, PrintWriter out) {
        int index = line.indexOf(" ");
        if (index != -1) {
            String code = line.substring(0, index);
            String time = line.substring(index + 1);
            // проверка на неактивность
            if (inactivityStart.isEmpty() && ("400".equals(code) || "500".equals(code))) {
                inactivityStart = time;
                inactivityStop = "";
            }
            // проверка на активность
            if (!inactivityStart.isEmpty() && inactivityStop.isEmpty() && ("200".equals(code) || "300".equals(code))) {
                inactivityStop = time;
                out.println(String.format("%s;%s", inactivityStart, inactivityStop));
                inactivityStart = "";
            }
        }
    }

    public void work() {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
                reader.lines().forEach(line -> processLine(line, out));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
