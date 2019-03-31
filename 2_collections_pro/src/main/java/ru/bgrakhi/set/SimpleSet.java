package ru.bgrakhi.set;

import ru.bgrakhi.list.DynamicContainer;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<E> implements Iterable<E> {

    DynamicContainer<E> data = new DynamicContainer<>();

    private boolean isUnique(E element) {
        boolean result = true;
        for (int i = 0; i < data.getSize(); i++) {
            E item = data.get(i);
            if (Objects.equals(element, item)) {
                result = false;
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
