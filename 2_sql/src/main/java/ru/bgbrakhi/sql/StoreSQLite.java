package ru.bgbrakhi.sql;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreSQLite implements Closeable {
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

            ByteArrayOutputStream writer = new ByteArrayOutputStream();

            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(
                    entries,
                    writer
            );

            System.out.println(writer.toString());

            String xsl = "<?xml version=\"1.0\"?>\n" +
                    "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n" +
                    "<xsl:template match=\"/\">\n" +
                    "<entries_new>" +
                    "   <xsl:for-each select=\"entries/data\">\n" +
                    "       <entry>" +
                    "           <xsl:attribute name=\"href\">" +
                    "               <xsl:value-of select=\"field\"/>" +
                    "           </xsl:attribute>" +
                    "       </entry>\n" +
                    "   </xsl:for-each>\n" +
                    " </entries_new>\n" +
                    "</xsl:template>\n" +
                    "</xsl:stylesheet>\n";
            String xml = writer.toString();

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = null;
            try {
                transformer = factory.newTransformer(
                        new StreamSource(
                        new ByteArrayInputStream(xsl.getBytes()))
                );
            } catch (TransformerConfigurationException e) {
                e.printStackTrace();
            }
            try {
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                transformer.transform(new StreamSource(
                        new ByteArrayInputStream(xml.getBytes())),
                        new StreamResult(System.out)
                );
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        }
    }

}