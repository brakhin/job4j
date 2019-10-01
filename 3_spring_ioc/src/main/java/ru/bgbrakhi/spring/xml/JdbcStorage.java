package ru.bgbrakhi.spring.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.Properties;

public class JdbcStorage implements IStorage {
    private static final Logger LOG = LogManager.getLogger(JdbcStorage.class);
    private Properties config;
    private Connection connection;

    @Autowired
    public JdbcStorage(IConfiguration configuration) {
        this.config = configuration.getProperties();
        try {
            Class.forName(this.config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    this.config.getProperty("url"),
                    this.config.getProperty("username"),
                    this.config.getProperty("password"));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long addUser(User user) {
        Long result = 0L;
        try {
            PreparedStatement statement = connection.prepareStatement("insert into users(name) values(?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getLong(1);
                user.setId(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void updateUser(Long id, User user) {

    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public User get(Long index) {
        return null;
    }
}
