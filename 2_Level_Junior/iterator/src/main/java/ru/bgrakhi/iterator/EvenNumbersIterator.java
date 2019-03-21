package ru.bgrakhi.iterator;

import java.util.*;

public class EvenNumbersIterator implements Iterator {

    private int[] data;
    private int index;

    public EvenNumbersIterator(final int[] data) {
        this.data = data;
        index = getLastEvenIndex(0);
    }

    @Override
    public boolean hasNext() {
        return index < data.length;
    }

    private int getLastEvenIndex(int fromIndex) {
        int result = data.length;
        for (int i = fromIndex; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                result = i;
                break;
            }
        }
        return result;
    }

    @Override
    public Object next() {
        int curindex = index;
        index = getLastEvenIndex(curindex + 1);
        if (curindex == data.length) {
            throw new NoSuchElementException();
        }
        return Integer.valueOf(data[curindex]);
    }
}
