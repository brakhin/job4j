package ru.bgbrakhi.multithread.monitor;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.bgrakhi.list.DynamicContainer;

import java.util.Iterator;

@ThreadSafe
public class DynList<E> extends DynamicContainerExt<E> {

    @GuardedBy("this")
    private DynamicContainerExt array;

    public DynList(DynamicContainerExt array) {
        this.array = array;
    }

    @Override
    public synchronized Iterator<E> iterator() {
        Iterator<E> result = null;
        try {
            result = copy(this.array).iterator();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }

    private DynamicContainerExt copy(DynamicContainerExt object) throws CloneNotSupportedException {
        return (DynamicContainerExt) object.clone();
    }
}
