package ru.bgbrakhi.spring.xml;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void whenLoadContextThenGetBeans() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        UserStorage userStorage = context.getBean(UserStorage.class);
        Long id = userStorage.addUser(new User("name"));
        assertNotNull(userStorage.get(id));
    }

    @Test
    public void whenUserAddThenCanFindIt() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        UserStorage userStorage = context.getBean(UserStorage.class);
        Long id = userStorage.addUser(new User("name"));
        assertThat(userStorage.get(id).getName(), is("name"));
    }

    @Test
    public void whenUserUpdateThenCanFindIt() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        UserStorage userStorage = context.getBean(UserStorage.class);
        Long id = userStorage.addUser(new User("name"));
        userStorage.updateUser(id, new User("name2"));
        assertThat(userStorage.get(id).getName(), is("name2"));
    }

    @Test
    public void whenUserDeleteThenCantFindIt() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        UserStorage userStorage = context.getBean(UserStorage.class);
        Long id = userStorage.addUser(new User("name"));
        assertNotNull(userStorage.get(id));

        userStorage.deleteUser(id);
        assertNull(userStorage.get(id));
    }
}