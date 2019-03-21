package ru.bgrakhi.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {

    private Iterator<Integer> curIterator = null;

    private void updateCurIterator(final Iterator<Iterator<Integer>> iterator) throws NoSuchElementException {
        if (iterator.hasNext()) {
            curIterator = iterator.next();
        }
    }

    public Iterator<Integer> convert(final Iterator<Iterator<Integer>> iterator) {

        if (curIterator == null) {
            updateCurIterator(iterator);
        }

        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return curIterator.hasNext();
            }

            @Override
            public Integer next() {
                Integer result = 0;
                if (curIterator.hasNext()) {
                    result = curIterator.next();
                    if (!curIterator.hasNext() &&  iterator.hasNext()) {
                        updateCurIterator(iterator);
                    }
                } else {
                    throw new NoSuchElementException();
                }
                return result;
            }
        };
    }
}
