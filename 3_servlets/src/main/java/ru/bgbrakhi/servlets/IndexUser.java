package ru.bgbrakhi.servlets;

import java.util.Objects;

public class IndexUser {
    private String firstname;
    private String lastname;
    private String sex;
    private String description;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IndexUser indexUser = (IndexUser) o;
        return Objects.equals(firstname, indexUser.firstname)
                && Objects.equals(lastname, indexUser.lastname)
                && Objects.equals(sex, indexUser.sex)
                && Objects.equals(description, indexUser.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, sex, description);
    }
}
