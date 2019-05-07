package ru.bgbrakhi.ood;

import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class EngeneerCalcTest {

    @Test
    public void mainTest() {
        EngeneerCalc ec = new EngeneerCalc(new InteractCalc());
        ec.calculate("+", 3);
        double res1 = ec.lastValue();
        ec.calculate("sin", 0);
        double res2 = ec.lastValue();
        ec.calculate("cos", 0);
        double res3 = ec.lastValue();
        ec.calculate("tg", 0);
        double res4 = ec.lastValue();
        ec.calculate("ctg", 0);
        double res5 = ec.lastValue();
        assertThat(res1, is(3.0));
        assertThat(res2, is(0.1411200080598672));
        assertThat(res3, is(0.9900590857598653));
        assertThat(res4, is(1.5238730174054593));
        assertThat(res5, is(0.04695777828054125));
    }
}