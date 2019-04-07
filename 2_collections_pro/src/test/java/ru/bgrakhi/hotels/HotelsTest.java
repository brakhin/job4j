package ru.bgrakhi.hotels;

import org.junit.Test;
import static org.junit.Assert.*;

public class HotelsTest {
    @Test
    public void mainTest() throws Exception {
        Hotels h = new Hotels();
        assertArrayEquals(h.calcStars(
                new int[]{100, 90, 10, 20, 50, 60, 40, 30, 80, 70}),
                new int[]{5, 5, 1, 1, 3, 3, 2, 2, 4, 4}
        );
    }
}