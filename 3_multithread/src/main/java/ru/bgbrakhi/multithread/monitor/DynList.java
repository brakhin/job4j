package ru.bgbrakhi.multithread.monitor;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.bgrakhi.list.DynamicContainer;

import java.util.Iterator;

@ThreadSafe
public class DynList<E> implements Iterable<E> {

    @GuardedBy("this")
    private DynamicContainer<E> container;

    public DynList() {
        this.container = new DynamicContainer<>();
    }

    @Override
    public synchronized Iterator<E> iterator() {
        Iterator<E> result = null;
        try {
            result = copy(this.container).iterator();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }

    private DynamicContainer copy(DynamicContainer<E> object) throws CloneNotSupportedException {
        return (DynamicContainer) object.clone();
    }

    public void add(E element) throws CloneNotSupportedException {
        synchronized (this.container) {
            this.container.add(element);
        }
    }
}