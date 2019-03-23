package ru.bgrakhi.set;

import ru.bgrakhi.list.DynamicContainer;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {

    DynamicContainer<E> data = new DynamicContainer<>();

    private boolean isUnique(E element) {
        boolean result = true;
        for (int i = 0; i < data.getSize(); i++) {
            if (data.get(i).equals(element)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void add(E element) {
        if (isUnique(element)) {
            data.add(element);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return data.iterator();
    }
}
