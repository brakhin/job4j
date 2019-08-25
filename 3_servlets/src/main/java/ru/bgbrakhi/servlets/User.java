package ru.bgbrakhi.servlets;

import java.util.Objects;

public class User {
    private int id;

    private String login;
    private String password;
    private int city = 1;
    private int role = 1;
    private String name;

    public User(int id, String login, String password, int city, Integer role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.city = city;
        this.role = role;
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public int getCity() {
        return city;
    }
    public int getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setCity(int city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id
                && role == user.role
                && Objects.equals(login, user.login)
                && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, role);
    }

    public String getName() {
        return name;
    }
}
