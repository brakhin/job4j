package ru.bgrakhi.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedContainer<E> implements Iterable<E> {

    SimpleArrayList<E> data;

    private int modCount = 0;
    private int size = 0;

    public LinkedContainer() {
        data = new SimpleArrayList();
    }

    public int size() {
        return data.getSize();
    }


    public void add(E value) {
        data.add(value);
        modCount++;
        size++;
    }

    public E get(int index) {
        return data.get(index);
    }

    public E delete() {
        return data.delete();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int index = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return index < data.getSize();
            }

            @Override
            public E next() {

                E result = null;

                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (expectedModCount < modCount) {
                    throw new ConcurrentModificationException();
                }
                result = data.get(index++);

                // усли итератор пуст - сбрасываем и синхронизируем счетчик изменений
                if (!hasNext()) {
                    modCount = 0;
                    expectedModCount = modCount;
                }

                return result;
            }
        };
    }
}
