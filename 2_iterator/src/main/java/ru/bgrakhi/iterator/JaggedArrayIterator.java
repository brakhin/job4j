package ru.bgrakhi.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class JaggedArrayIterator implements Iterator {

    private final int[][] data;
    private int index1;
    private int index2;

    public JaggedArrayIterator(final int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return index1 < data.length && index2 < data[index1].length;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Object result = data[index1][index2];
        if (index2 < data[index1].length - 1) {
            index2++;
        } else {
            index2 = 0;
            index1++;
        }
        return result;
    }
}
