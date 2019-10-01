package ru.bgbrakhi.spring.xml.beanex.xmlfabricmethod;

import org.springframework.beans.factory.annotation.Autowired;

public class Object2 {

    private final Object1 obj;

    @Autowired
    public static Object2 builder(Object1 configuration) {
        return new Object2(configuration);
    }

    private Object2(Object1 configuration) {
        this.obj = configuration;
    }
}
