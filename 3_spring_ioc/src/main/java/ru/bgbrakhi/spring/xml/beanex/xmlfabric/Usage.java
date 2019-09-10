package ru.bgbrakhi.spring.xml.beanex.xmlfabric;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.bgbrakhi.spring.xml.ConfigObj;
import ru.bgbrakhi.spring.xml.JdbcStorage;
import ru.bgbrakhi.spring.xml.UserStorage;

public class Usage {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        ConfigObj config = context.getBean(ConfigObj.class);
        JdbcStorage jdbcStorage = context.getBean(JdbcStorage.class);
        UserStorage userStorage = context.getBean(UserStorage.class);
    }
}
