package ru.bgbrakhi.spring.xml.beanex.xmlfabric;

import org.springframework.beans.factory.annotation.Autowired;

public class BeanFactory {

    @Autowired
    public Object1 createObject1() {
        return new Object1();
    }

    @Autowired
    public Object2 createObject2(Object1 object1) {
        return new Object2(object1);
    }
}
