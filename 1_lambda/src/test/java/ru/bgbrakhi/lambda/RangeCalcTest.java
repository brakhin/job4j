package ru.bgbrakhi.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class RangeCalcTest {

    @Test
    public void whenLinerFunction() {
        RangeCalc rc = new RangeCalc();
        List<Double> result = rc.diapason(1, 4, x -> 3 * x + 1);
        List<Double> expected = Arrays.asList(4d, 7d, 10d);
        assertThat(result, is(expected));
    }
    @Test
    public void whenSquareFunction() {
        RangeCalc rc = new RangeCalc();
        List<Double> result = rc.diapason(1, 4, x -> 2 * Math.pow(x, 2) + 3 * x + 1);
        List<Double> expected = Arrays.asList(6d, 15d, 28d);
        assertThat(result, is(expected));
    }
    @Test
    public void whenLogFunction() {
        RangeCalc rc = new RangeCalc();
        List<Double> result = rc.diapason(1, 4, x -> Math.log(x));
        List<Double> expected = Arrays.asList(0d, 0.6931471805599453d, 1.0986122886681098d);
        assertThat(result, is(expected));
    }
}
