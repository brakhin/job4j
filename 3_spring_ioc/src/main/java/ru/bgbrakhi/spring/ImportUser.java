package ru.bgbrakhi.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ImportUser {
    private UserStorage userStorage;

    public ImportUser() {
        init();
    }
    public Long addUser(User user) {
        return userStorage.addUser(user);
    }

    private void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        userStorage = context.getBean(UserStorage.class);
    }

    public static void main(String[] args) {
        ImportUser importUser = new ImportUser();
        importUser.addUser(new User("name_1"));
        importUser.addUser(new User("name_2"));
        importUser.addUser(new User("name_3"));
    }
}
