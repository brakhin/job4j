package ru.job4j.list;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SortUser {
    public Set<User> sort(List<User> users) {
        Set<User> result = new TreeSet<>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if (o1.getAge() < o2.getAge()) return -1;
                else if (o1.getAge() > o2.getAge()) return 1;
                else return 0;
            }
        });
        result.addAll(users);
        return result;
    }

    public List<User> sortNameLength(List<User> users) {
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(o1.getAge(), o2.getAge());
            }
        });
        return users;
    }


    /**
     *  Компаратор по имени
     */
    class nameComparator implements Comparator<User> {
        @Override
        public int compare(User o1, User o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    /**
     *  Компаратор по возрасту
     */
    class ageComparator implements Comparator<User> {
        @Override
        public int compare(User o1, User o2) {
            return Integer.compare(o1.getAge(), o2.getAge());
        }
    }

    public List<User> sortByAllFields(List<User> users) {
        users.sort(new nameComparator().thenComparing(new ageComparator()));
        return users;
    }
}
