package ru.bgbrakhi.gc.cash;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cache {
    public static final String LN = System.getProperty("line.separator");
    private Map<String, SoftReference<String>> cash;

    public Cache() {
        cash = new HashMap<>();
    }

    public String getFileText(String fileName) {
        String result = "";
        SoftReference<String> softString = cash.get(fileName);
        if (softString == null) {
            result = readFile(fileName);
            cash.put(fileName, new SoftReference<>(result));
        } else {
            result = softString.get();
        }
        return result;
    }

    private String readFile(String fileName) {
        String result = "";
        StringBuilder builder = new StringBuilder();
        String line;
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            while ((line = in.readLine()) != null) {
                builder.append(line).append(LN);
            }
            result = builder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
