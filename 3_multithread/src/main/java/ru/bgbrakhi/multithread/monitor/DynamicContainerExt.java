package ru.bgbrakhi.multithread.monitor;

import ru.bgrakhi.list.DynamicContainer;

public class DynamicContainerExt<E> extends DynamicContainer<E> implements Cloneable{

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
