package ru.bgbrakhi.sql;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoreSQLite implements Closeable {

    @XmlRootElement
    public static class Entries {
        private List<Entry> data;

        public Entries() {
        }

        public Entries(List<Entry> data) {
            this.data = data;
        }

        public List<Entry> getData() {
            return data;
        }

        public void setData(List<Entry> data) {
            this.data = data;
        }
    }

    @XmlRootElement
    public static class Entry {
        private int field;

        public Entry() {
        }

        public Entry(Integer field) {
            this.field = field;
        }

        public Integer getField() {
            return field;
        }
    }

    private final Connection connection;
    private static final boolean AUTO_COMMIT = false;

    public StoreSQLite(Connection connection) {
        this.connection = connection;
        init();
    }

    private void init() {
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select name from sqlite_master where type = 'table' and name = \"entry\"")
        ) {
            connection.setAutoCommit(AUTO_COMMIT);
            if (!rs.next()) {
                statement.execute(" create table entry (field integer);");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generate(int size) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("delete from entry;");
            for (int i = 0; i < size; i++) {
                statement.executeUpdate(String.format("insert into entry(field) values(%d);", i));
            }
            if (!AUTO_COMMIT) {
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Entry> load() {
        List<Entry> result = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select field from entry;")
        ) {
            while (rs.next()) {
                result.add(new Entry(rs.getInt("field")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws SQLException, JAXBException {
        String url = "jdbc:sqlite:sample.db";
        try (Connection connection = DriverManager.getConnection(url);
            StoreSQLite store = new StoreSQLite(connection)) {

            // тайминг
            long startTime = System.currentTimeMillis();
            store.generate(3);
            long stopTime = System.currentTimeMillis();
            long timeDelta = stopTime - startTime;

            List<Entry> data = store.load();
            Entries entries = new Entries(data);

            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(
                    entries,
                    System.out
            );
        }
    }

}