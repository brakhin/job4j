package ru.bgbrakhi.gc;

import ru.job4j.calculator.Calculator;

import java.util.StringJoiner;

public class MemoryUsage {

    public static class User {
        private String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println(String.format("destroy object user %s", this));
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static class UserEmpty {
    }

    public static void main(String[] args) {

        info();
        User u1 = new User("12345");
        info();
        User u2 = new User("12345");
        info();


        User[] data = new User[10000];

        System.out.println("start");
        info();

        for (int i = 0; i < 1000; i++) {
            data[i] = new User("test" + i);
        }

        for (int i = 1000; i > 300; i--) {
            data[i] = null;
        }

        for (int i = 0; i < 1000; i++) {
            data[1000 + i] = new User("test" + i);
        }

        for (int i = 1000; i > 300; i--) {
            data[1000 + i] = null;
        }

        info();
//        SoftReference<User> user = new SoftReference(new User("test"));
//        System.out.println(user.get().name);
//        System.out.println(user);
//        user = null;
//        System.gc();
        System.out.println("finish");
    }

    public static void info() {
        int mb = 1 + 0 * 1024 * 1024;

        Runtime runtime = Runtime.getRuntime();
        System.out.println("Heap utilization statistics (MB)");
        System.out.println("Used Memory : " + (runtime.totalMemory() - runtime.freeMemory()) / mb);
        System.out.println("Free Memory : " + runtime.freeMemory() / mb);
        System.out.println("Total Memory : " + runtime.totalMemory() / mb);
        System.out.println("Max memory : " + runtime.maxMemory() / mb);
    }
}
