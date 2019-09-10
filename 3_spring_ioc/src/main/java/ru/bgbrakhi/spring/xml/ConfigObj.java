package ru.bgbrakhi.spring.xml;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.Properties;

public class ConfigObj implements IConfiguration {
    private final Properties config = new Properties();

    @Autowired
    public ConfigObj() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Properties getProperties() {
        return config;
    }
}
