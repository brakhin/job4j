package ru.bgbrakhi.spring.xml.beanex.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.bgbrakhi.spring.xml.ConfigObj;
import ru.bgbrakhi.spring.xml.JdbcStorage;
import ru.bgbrakhi.spring.xml.UserStorage;

public class Usage {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext();
        Object1 object1 = context.getBean(Object1.class);
        Object2 object2 = context.getBean(Object2.class);
        Object3 object3 = context.getBean(Object3.class);
    }
}
