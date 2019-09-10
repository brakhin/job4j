package ru.bgbrakhi.spring.xml.beanex.annotation;

import org.springframework.beans.factory.annotation.Autowired;

public class Object2 {

    private final Object1 obj;

    @Autowired
    public Object2(Object1 obj) {
        this.obj = obj;
    }
}
