package ru.bgrakhi.generic;

public class UserStore extends AbstractStore<User> {
    protected UserStore(SimpleArray<User> data) {
        super(data);
    }
}
