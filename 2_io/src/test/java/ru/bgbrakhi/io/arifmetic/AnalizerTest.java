package ru.bgbrakhi.io.arifmetic;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizerTest {

    @Test
    public void testCanBeEqualTo24() {
        Analizer analizer = new Analizer();
        assertThat(analizer.canBeEqualToValue(new int[]{4, 1, 8, 7}, 24), is(true));
    }

    @Test
    public void testCanNotEqualTo24() {
        Analizer analizer = new Analizer();
        assertThat(analizer.canBeEqualToValue(new int[]{1, 2, 1, 2}, 24), is(false));
    }
}