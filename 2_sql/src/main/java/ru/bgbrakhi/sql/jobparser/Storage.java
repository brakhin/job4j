package ru.bgbrakhi.sql.jobparser;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.postgresql.jdbc2.optional.ConnectionPool;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class Storage {
    private static final Logger LOG = LogManager.getLogger(Storage.class);
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final Storage INSTANCE = new Storage();
    private final Properties config = new Properties();

    private Storage() {
        initConfig();
    }

    public static Storage getInstance() {
        return INSTANCE;
    }

    private void initConfig() {
        try (InputStream is = Storage.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(is);
            SOURCE.setUrl(config.getProperty("jdbc.url"));
            SOURCE.setUsername(config.getProperty("jdbc.username"));
            SOURCE.setPassword(config.getProperty("jdbc.password"));
            SOURCE.setMinIdle(5);
            SOURCE.setMaxIdle(10);
            SOURCE.setMaxOpenPreparedStatements(100);
            Class.forName(config.getProperty("jdbc.driver"));
        } catch (ClassNotFoundException | IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public boolean saveData(Vacancy vacancy) {
        boolean result = true;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement stat = connection.prepareStatement(
                     "insert into vacancy(name, text, link, vacancytime) values(?, ?, ?, ?);")
        ) {
            stat.setString(1, vacancy.getName());
            stat.setString(2, vacancy.getText());
            stat.setString(3, vacancy.getLink());
            stat.setLong(4, vacancy.getVacancytime());
            stat.executeUpdate();
            LOG.info(String.format("Add vacancy \"%s\"", vacancy.getLink()));
        } catch (SQLException e) {
            result = false;
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public long getLastTime() {
        long result = 0L;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement stat = connection.prepareStatement("select max(vacancytime) from vacancy;")) {
                ResultSet rs = stat.executeQuery();
                result = rs.next() ? rs.getLong(1) : 0L;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
}
