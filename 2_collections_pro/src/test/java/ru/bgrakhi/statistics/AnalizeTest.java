package ru.bgrakhi.statistics;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizeTest {

    @Test
    public void infoTest() {
        List<Analize.User> previous = new LinkedList<>();
        previous.add(new Analize.User(1, "Bob"));
        previous.add(new Analize.User(3, "Alex"));
        previous.add(new Analize.User(5, "Olga"));
        previous.add(new Analize.User(9, "Ann"));
        previous.add(new Analize.User(11, "Invariant"));

        List<Analize.User> current = new LinkedList<>();
        current.add(new Analize.User(1, "Bob_New"));
        current.add(new Analize.User(4, "Alexey"));
        current.add(new Analize.User(5, "Olga_New"));
        current.add(new Analize.User(10, "Nikolay"));
        current.add(new Analize.User(11, "Invariant"));

        Analize analize = new Analize();
        Analize.Info info = analize.diff(previous, current);

        assertThat(info.added, is(2));
        assertThat(info.deleted, is(2));
        assertThat(info.changed, is(2));
    }
}