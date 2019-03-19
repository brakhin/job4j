package ru.bgrakhi.iterator;

import java.util.*;
import java.util.stream.Collectors;

public class EvenNumbersIterator implements Iterator {

    private List<Integer> data;
    private int index;
    private int evenValuesCount;

    public EvenNumbersIterator(final int[] data) {
        this.data = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            this.data.add(data[i]);
        }
        this.data = this.data
                .stream()
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toList());
    }

    @Override
    public boolean hasNext() {
        return index < data.size();
    }

    @Override
    public Object next() {
        if (hasNext()) {
            return data.get(index++);
        } else {
            throw new NoSuchElementException();
        }
    }
}
