package ru.bgrakhi.iterator;

import java.util.*;

public class EvenNumbersIterator implements Iterator {

    private int[] data;
    private int index;

    public EvenNumbersIterator(final int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = index; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                result = true;
                index = i;
                break;
            }
        }
        return result;
    }

    @Override
    public Object next() {

        if (!hasNext()) {
            throw new NoSuchElementException();
        } else {
            return data[index++];
        }
    }
}
