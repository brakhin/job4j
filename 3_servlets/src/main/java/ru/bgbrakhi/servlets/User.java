package ru.bgbrakhi.servlets;

import java.util.Date;
import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String login;
    private String email;
    private Date createDate;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
<<<<<<< HEAD
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(login, user.login) &&
                Objects.equals(email, user.email) &&
                Objects.equals(createDate, user.createDate);
=======
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id
                && Objects.equals(name, user.name)
                && Objects.equals(login, user.login)
                && Objects.equals(email, user.email)
                && Objects.equals(createDate, user.createDate);
>>>>>>> 74a88dd... 1. Перенести все виды из предыдущего задания на JSP[#147112]
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, email, createDate);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }
}
