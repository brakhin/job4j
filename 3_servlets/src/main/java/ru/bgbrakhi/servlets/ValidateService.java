package ru.bgbrakhi.servlets;

import java.util.concurrent.CopyOnWriteArrayList;

public class ValidateService {
    private static final ValidateService INSTANCE = new ValidateService();
    private final IStore store = DbStore.getInstance();

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    public Boolean add(User user) {
        return store.add(user);
    }

    public Boolean update(User user) {
        if (store.findById(user.getId()) != null) {
            return store.update(user);
        }
        return false;
    }

    public Boolean delete(User user) {
        if (store.findById(user.getId()) != null) {
            return store.delete(user);
        }
        return false;
    }

    public CopyOnWriteArrayList<User> findAll() {
        return store.findAll();
    }

    public User findById(User user) {
        return store.findById(user.getId());
    }

    public Integer isCredentional(String login, String password) {
        return store.isCredentional(login, password);
    }


}
