package ru.job4j.list;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StreamApiDemoTest {
    @Test
    public void testCalc() {
        StreamApiDemo sad = new StreamApiDemo(new Integer[]{1, 3, 2, 5, 7, 12, 3, 78, 10});
        Integer result = sad.doCalc();
        assertThat(result, is(6332));
    }
}