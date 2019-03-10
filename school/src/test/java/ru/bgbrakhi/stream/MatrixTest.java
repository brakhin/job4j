package ru.bgbrakhi.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MatrixTest {

    @Test
    public void matrixTest() {
        Matrix matrix = new Matrix();
        List<Integer> result = matrix.toFlatList();
        assertThat(result, is(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8)));
    }
}
