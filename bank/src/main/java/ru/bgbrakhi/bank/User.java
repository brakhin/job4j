package ru.bgbrakhi.bank;

public class User {
    String name;
    String passport;

    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    @Override
    public boolean equals(Object o) {
        return (o != null && getClass() == o.getClass() && this.passport.equals(((User)o).passport));
    }

    @Override
    public int hashCode() {
        return this.passport.hashCode();
    }
}
