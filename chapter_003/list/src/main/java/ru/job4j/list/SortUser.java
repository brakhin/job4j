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
}
