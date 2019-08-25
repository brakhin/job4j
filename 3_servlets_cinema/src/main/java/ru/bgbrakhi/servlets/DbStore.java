package ru.bgbrakhi.servlets;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.CopyOnWriteArrayList;

public class DbStore implements IStore {

    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbStore INSTANCE = new DbStore();

    private DbStore() {
        SOURCE.setUrl("jdbc:sqlite:db\\\\servlets.db");
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
    public boolean updateSeat(Seat seat, Connection outerConnection) {
        boolean result = true;
        try (PreparedStatement st = outerConnection.prepareStatement("update hall set line=?, col=?, id_account=? where id=?;")) {
            st.setInt(1, seat.getLine());
            st.setInt(2, seat.getCol());
            st.setLong(3, seat.getIdAccount());
            st.setInt(4, seat.getId());
            st.execute();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public CopyOnWriteArrayList<Seat> getSeats() {
        CopyOnWriteArrayList<Seat> result = new CopyOnWriteArrayList<>();
        try (Connection connection = SOURCE.getConnection();
            PreparedStatement st = connection.prepareStatement("SELECT * FROM hall order by line, col;")) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(new Seat(
                        rs.getInt("id"),
                        rs.getInt("line"),
                        rs.getInt("col"),
                        rs.getInt("id_account"))
                );
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Seat getSeat(int id) {
        Seat result = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(String.format("SELECT * FROM hall where id = %d;", id));
             ResultSet rs = st.executeQuery()) {

            if (rs.next()) {
                result = new Seat(
                        rs.getInt("id"),
                        rs.getInt("line"),
                        rs.getInt("col"),
                        rs.getInt("id_account"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void createAccount(Account account) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("insert into account(username, phone) values(?, ?);", Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(false);
            st.setString(1, account.getUsername());
            st.setString(2, account.getPhone());
            st.execute();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                Long accountId = rs.getLong(1);
                Seat seat = getSeat(account.getSeatid());
                if (seat != null) {
                    seat.setAccount(accountId);
                    if (updateSeat(seat, connection)) {
                        connection.commit();
                    }
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
