package ru.job4j.list;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StreamApiDemo {
    private Integer[] data;

    public StreamApiDemo(Integer[] data) {
        this.data = data;
    }

    public Integer doCalc() {
        return Arrays.asList(data).stream()
                .filter(i -> i % 2 == 0)
                .map(i -> i*i)
                .reduce(0, Integer::sum);
    }
}
