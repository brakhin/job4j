package ru.bgbrakhi.servlets;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public class DbStore implements IStore {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbStore INSTANCE = new DbStore();

    public DbStore() {
        SOURCE.setUrl("jdbc:sqlite:db\\\\database.db");
        SOURCE.setUsername("");
        SOURCE.setPassword("");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DbStore getInstance() {
        return INSTANCE;
    }

    @Override
    public Boolean add(User user) {
        Boolean result = false;
        try (Connection connection = SOURCE.getConnection();
            PreparedStatement st = connection.prepareStatement("insert into users(login, password, role) values(?, ?, ?);")) {
            st.setString(1, user.getLogin());
            st.setString(2, user.getPassword());
            st.setInt(3, user.getRole());
            st.execute();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Boolean update(User user) {

        Boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("update users set login=?, password=?, role=? where id=?;")) {

            st.setString(1, user.getLogin());
            st.setString(2, user.getPassword());
            st.setInt(3, user.getRole());
            st.setInt(4, user.getId());
            st.execute();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Boolean delete(User user) {
        Boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("delete users where id=?;")) {

            st.setInt(1, user.getId());
            st.execute();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User findById(Integer id) {
        User result = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("select * from users where id=?;")) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result = new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getInt("role")
                );
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public CopyOnWriteArrayList<User> findAll() {
        CopyOnWriteArrayList<User> result = new CopyOnWriteArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("select * from users;")) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getInt("role"))
                );
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer isCredentional(String login, String password) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("select id from users where login=? and password=?;")) {

            st.setString(1, login);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}