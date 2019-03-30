package ru.bgrakhi.statistics;

import java.util.*;

public class Analize {

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;
    }

    private Map<Integer, User> toMap(List<User> list) {
        Map<Integer, User> result = new HashMap<>();
        for (User user : list) {
            result.put(user.id, user);
        }
        return result;
    }

    public Info diff(List<User> previous, List<User> current) {
        Info result = new Info();

        Map<Integer, User> prevmap = toMap(previous);
        Map<Integer, User> curmap = toMap(current);

        for (Integer key : prevmap.keySet()) {
            User curuser = curmap.get(key);
            if (curuser == null) {
                // удаление
                result.deleted++;
            } else {
                // изменение
                if (!prevmap.get(key).name.equals(curmap.get(key).name)) {
                    result.changed++;
                }
            }
        }

        // добавления
        for (Integer key : curmap.keySet()) {
            User curuser = prevmap.get(key);
            if (curuser == null) {
                result.added++;
            }
        }
        return result;
    }
}
