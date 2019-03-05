package ru.job4j.list;

public class User {
    private Integer id;
    private String name;
    private String city;
    private int age;

    public User(Integer id, String name, int age, String city) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public Integer getId() {
        return this.id;
    }

    public int getAge() {
        return this.age;
    }
}
