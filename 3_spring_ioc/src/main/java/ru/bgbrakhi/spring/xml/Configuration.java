package ru.bgbrakhi.spring.xml;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.Properties;

public class Configuration implements IConfiguration {
    private final Properties config = new Properties();

    @Autowired
    public Configuration() {
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
