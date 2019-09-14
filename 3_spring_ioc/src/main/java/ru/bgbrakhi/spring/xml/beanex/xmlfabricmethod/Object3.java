package ru.bgbrakhi.spring.xml.beanex.xmlfabricmethod;

import org.springframework.beans.factory.annotation.Autowired;

public class Object3 {

    private final Object2 obj;

    @Autowired
    public Object3(Object2 storage) {
        this.obj = storage;
    }
}
