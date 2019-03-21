package ru.bgrakhi.iterator;

import java.util.Arrays;
import java.util.Iterator;

public class SimpleArray<T>  implements Iterable<T> {
    private Object[] data;
    private int size;

    public SimpleArray(int initialsize) {
        this.size = initialsize;
        data = (T[]) new Object[this.size];
    }

    public void add(T element) {
        data = Arrays.copyOf(data, data.length + 1);
        data[size++] = element;
    }

    public void set(T element, int index) {
        Arrays.copyOf(data, data.length + 1);
        for (int i = index; i < data.length; i++) {
            data[i + 1] = data[i];
        }
        data[index] = element;
    }

    public void remove(int index) {
        for (int i = index; i < data.length; i++) {
            data[i] = data[i + 1];
            data = Arrays.copyOf(data, data.length - 1);
            size--;
        }
    }

    public T get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IndexOutOfBoundsException();
        }
        return (T)data[index];
    }

    @Override
    public Iterator<T> iterator() {
        return (Iterator<T>) Arrays.asList(data).iterator();
    }
}
