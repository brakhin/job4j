package ru.bgbrakhi.sql.jobparser;

import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Jsoup.class})
@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.dom.*"})
public class ParserTest {

    @Test
    public void whenParseFirstPageThenReturnTheLinkFirst() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("page.html");
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        String html = stringBuilder.toString();

        Connection connection = Mockito.mock(Connection.class);
        Mockito.when(connection.get()).thenReturn(Jsoup.parse(html));

        PowerMockito.mockStatic(Jsoup.class);
        PowerMockito.when(Jsoup.connect(Mockito.anyString())).thenReturn(connection);

        Parser parser = new Parser();
        List<Vacancy> list = parser.parse(0, "https://www.sql.ru/forum/job/1");

        assertThat(list.size(), is(3));
        assertThat(list.get(0).getLink(),
                is("https://www.sql.ru/forum/1316319/ishhu-mesto-stazhera-na-spark-pyspark-java-v-etl"));
    }
}