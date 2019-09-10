package ru.bgbrakhi.spring.xml.beanex.xml;

import org.springframework.beans.factory.annotation.Autowired;

public class Object2 {

    private final Object1 obj;

    @Autowired
    public Object2(Object1 configuration) {
        this.obj = configuration;
    }
}
