package ru.bgbrakhi.lambda;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.function.Function;

public class RangeCalc {
    List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> result = new ArrayList<>();
        for (int i = start; i < end; i++) {
            result.add(func.apply((double) i));
        }
        return result;
    }

    public static void main(String[] args) {
        RangeCalc rc = new RangeCalc();
        List<Double> l1 = rc.diapason(1, 10, x -> 3 * x + 1);
        List<Double> l2 = rc.diapason(1, 10, x -> 2 * Math.pow(x, 2) + 3 * x + 1);
        List<Double> l3 = rc.diapason(1, 10, x -> Math.log(x));
    }
}
