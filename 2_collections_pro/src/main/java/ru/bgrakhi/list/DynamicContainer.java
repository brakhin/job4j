package ru.bgrakhi.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicContainer<E> implements Iterable<E> {

    private Object[] container = new Object[] {null};
    private int size = 0;
    private int modCount = 0;

    private Object[] grow(int sizeDelta) {
        Object[] result = container;
        if (size == container.length) {
            result = Arrays.copyOf(container, container.length + Math.max(1, sizeDelta));
            modCount++;
        }
        return result;
    }

    public int getSize() {
        return size;
    }

    public void add(E element) {
        container = grow(container.length);
        container[size++] = element;
        modCount++;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) container[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int expectedModCount = modCount;
            private int iteratorIndex = 0;

            @Override
            public boolean hasNext() {
                return iteratorIndex < container.length;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (expectedModCount < modCount) {
                    throw new ConcurrentModificationException();
                }
                return (E) container[iteratorIndex++];
            }
        };
    }
}
