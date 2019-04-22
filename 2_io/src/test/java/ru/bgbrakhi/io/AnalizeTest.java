package ru.bgbrakhi.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringJoiner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AnalizeTest {
    @Test
    public void testWork() throws IOException {
        Analize analize = new Analize("server.log_", "unavailable.csv");
        analize.work();
        try (BufferedReader reader = new BufferedReader(new FileReader("unavailable.csv"))) {
            StringJoiner builder = new StringJoiner("\n");
            reader.lines().forEach(builder::add);
            assertThat(builder.toString(), is("10:58:01;10:59:01\n11:01:02;11:02:02"));
        }
    }
}