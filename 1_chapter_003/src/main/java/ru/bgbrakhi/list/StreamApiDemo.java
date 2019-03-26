package ru.bgbrakhi.list;

import java.util.Arrays;

public class StreamApiDemo {
    private Integer[] data;

    public StreamApiDemo(Integer[] data) {
        this.data = data;
    }

    public Integer calc() {
        return Arrays.asList(data).stream()
                .filter(i -> i % 2 == 0)
                .map(i -> i * i)
                .reduce(0, Integer::sum);
    }
}