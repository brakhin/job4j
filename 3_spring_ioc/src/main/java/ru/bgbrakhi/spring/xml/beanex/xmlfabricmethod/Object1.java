package ru.bgbrakhi.spring.xml.beanex.xmlfabricmethod;

import org.springframework.beans.factory.annotation.Autowired;

public class Object1 {

    @Autowired
    public static Object1 builder () {
        return new Object1();
    }

    private Object1() {
    }
}
