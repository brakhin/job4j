package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {
    @Test
    public void whenArrayThenSortArray() {
        int[] arr = new int[] {5, 4, 3, 2, 1};
        BubbleSort bs = new BubbleSort();
        int[] res = bs.sort(arr);
        assertThat(res, is(new int[] {1, 2, 3, 4, 5}));
    }

}