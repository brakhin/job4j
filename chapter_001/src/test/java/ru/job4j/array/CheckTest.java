package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CheckTest {

    @Test
    public void whenArrayBoolValThenMultElementsODD() {
        boolean[] array = new boolean[] {true, false, true, true};
        Check check = new Check();
        boolean result = check.mono(array);
        assertThat(result, is(false));
    }

    @Test
    public void whenArrayBoolValThenMultElementsNotODD() {
        boolean[] array = new boolean[] {true, true, true};
        Check check = new Check();
        boolean result = check.mono(array);
        assertThat(result, is(true));
    }
}