package ru.bgbrakhi.servlets;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryStore implements IStore {
    private static final MemoryStore INSTANCE = new MemoryStore();
    private CopyOnWriteArrayList<User> users = new CopyOnWriteArrayList<>();
    private AtomicInteger id = new AtomicInteger(0);

    public static MemoryStore getInstance() {
        return INSTANCE;
    }

    @Override
    public Boolean add(User user) {
        user.setId(id.getAndIncrement());
        return users.add(user);
    }

    @Override
    public Boolean update(User user) {
        User user_ = findById(user.getId());
        if (user_ != null) {
            int index = users.indexOf(user_);
            users.set(index, user_);
        }
        return user != null;
    }

    @Override
    public Boolean delete(User user) {
        User user_ = findById(user.getId());
        if (user_ != null) {
            int index = users.indexOf(user_);
            users.remove(index);
        }
        return user != null;
    }

    @Override
    public User findById(Integer id) {
        User result = null;
        for (User user : users) {
            if (user.getId() == id) {
                result = user;
                break;
            }
        }
        return result;
    }

    @Override
    public CopyOnWriteArrayList<User> findAll() {
        return users;
    }
}
