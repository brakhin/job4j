package ru.bgrakhi.generic;

import java.util.Arrays;
import java.util.Iterator;

public class SimpleArray<T>  implements Iterable<T> {
    private Object[] data;
    private int size;

    public SimpleArray(int initialsize) {
        this.size = initialsize;
        data = (T[]) new Object[this.size];
    }

    private Object[] grow(int sizeDelta) {
        Object[] result = data;
        if (size == data.length) {
            result = Arrays.copyOf(data, data.length + sizeDelta);
        }
        return result;
    }

    public void add(T element) {
        data = grow(data.length);
        data[size++] = element;
    }

    public void set(T element, int index) {
        if (index < size) {
            data[index] = element;
        }
    }

    public void remove(int index) {
        System.arraycopy(data, index + 1, data, index, data.length - index - 1);
        data = Arrays.copyOf(data, data.length - 1);
        size--;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) data[index];
    }

    public int length() {
        return data.length;
    }

    @Override
    public Iterator<T> iterator() {
        return (Iterator<T>) Arrays.asList(data).iterator();
    }
}
