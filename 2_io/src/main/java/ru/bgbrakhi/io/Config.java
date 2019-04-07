package ru.bgbrakhi.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private String path;
    private Map<String, String> values = new HashMap<>();

    public Config(String path)  {
        this.path = path;
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    private void parceString(String str) {
        int index = str.indexOf("=");
        if (index != -1) {
            values.put(str.substring(0, index), str.substring(index + 1));
        }
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.lines().forEach(this::parceString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }
}
