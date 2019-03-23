package ru.bgrakhi.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserTest {

    public static class User {
        private String name;
        private int children;
        private Calendar birthday;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return children == user.children &&
                    Objects.equals(name, user.name) &&
                    Objects.equals(birthday, user.birthday);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, children, birthday);
        }


        public User(String name, int children) {
            this.name = name;
            this.children = children;
        }
    }

    @Test
    public void testUser1() {
        User user1 = new User("name", 1);
        User user2 = new User("name", 1);

        Map<User, Object> map = new HashMap<>();
        map.put(user1, "object_1");
        map.put(user2, "object_1");

        System.out.print(map);

        // <БЕЗ ПЕРЕОПРЕДЕЛЕНИЯ МЕТОДОВ equals(), hashCode(): Несмотря на идентичность значений объектов отображены хеш-коды User, которые отличаются, т.к. объекты имеют разные ссылки
        // С ПЕРЕОПРЕДЕЛЕНИЕМ hashCode(): User имеют одинаковые hashCode, но объекты неуникальны, т.к. equals() не переопределено и по умолчанию equals сверет ссылки - объекты, которые не совпадают
        // С ПЕРЕОПРЕДЕЛЕНИЕМ equals(): hashcode() разный, т.к вычисляется от объектов - ссылок --> объекты уникальны (разные)
        // С ПЕРЕОПРЕДЕЛЕНИЕМ equals(), hashCode(): hashcode() одинаковый, т.к вычисляется от значений объектов, equals() одинаковый, т.к вычисляется от значений объектов --> объекты не уникальны (одинаковые)
    }
}