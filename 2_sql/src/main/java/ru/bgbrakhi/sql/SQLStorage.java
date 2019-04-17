package ru.bgbrakhi.sql;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Properties;

import static java.sql.DriverManager.getConnection;

public class SQLStorage {


    public static final Logger Log = LogManager.getLogger(SQLStorage.class.getName());

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/trackstudio";
        String username = "postgres";
        String password = "password";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM user1");
            while (rs.next()) {
                System.out.print("Column 1 returned ");
                System.out.println(rs.getString("user_name"));
            }
            rs.close();
            st.close();


        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Log.error(e.getMessage(), e);
                }

            }
        }

    }
}
