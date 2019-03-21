package ru.bgrakhi.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {
    private Iterator<Integer> curIterator = null;

    public Iterator<Integer> convert(final Iterator<Iterator<Integer>> iterator) {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                if (iterator.hasNext()) {
                    if (curIterator == null || !curIterator.hasNext()) {
                        do {
                            curIterator = iterator.next();
                        } while (!curIterator.hasNext() && iterator.hasNext());
                    }
                }
                return curIterator != null ? curIterator.hasNext() : false;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    return curIterator.next();
                }
            }
        };
    }
}
