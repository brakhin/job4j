package ru.bgbrakhi.spring;

import java.io.InputStream;
import java.util.Properties;

public class Configuration implements IConfiguration {
    private final Properties config = new Properties();

    public Configuration() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
            int a = 0;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Properties getProperties() {
        return config;
    }
}
