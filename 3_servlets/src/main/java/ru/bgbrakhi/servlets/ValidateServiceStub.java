package ru.bgbrakhi.servlets;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class ValidateServiceStub extends ValidateService {
    private final Map<Integer, User> store = new HashMap<>();
    private int ids = 0;

    @Override
    public Boolean add(User user) {
        user.setId(this.ids++);
        this.store.put(user.getId(), user);
        return true;
    }

    @Override
    public Boolean update(User user) {
        if (store.get(user.getId()) != null) {
            store.put(user.getId(), user);
            return true;
        }
        return false;
    }

    @Override
    public CopyOnWriteArrayList<User> findAll() {
        return new CopyOnWriteArrayList<User>(this.store.values());
    }
}
