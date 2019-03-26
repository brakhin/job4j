package ru.bgrakhi.list;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class DynamicContainerTest {

    DynamicContainer<Integer> data;

    @Before
    public void initial() {
        data = new DynamicContainer<>();
        data.add(1);
        data.add(2);
        data.add(3);
        data.add(4);
        data.add(5);
    }

    @Test
    public void checkIterator() {
        Iterator<Integer> iterator1 = data.iterator();
        assertThat(iterator1.hasNext(), is(true));
        assertThat(iterator1.next(), is(1));
        assertThat(iterator1.next(), is(2));
    }
}