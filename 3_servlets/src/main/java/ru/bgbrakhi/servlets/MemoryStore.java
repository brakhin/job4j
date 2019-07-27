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
<<<<<<< HEAD
        User user_ = findById(user.getId());
        if (user_ != null) {
            int index = users.indexOf(user_);
=======
        User user1 = findById(user.getId());
        if (user1 != null) {
            int index = users.indexOf(user1);
>>>>>>> 74a88dd... 1. Перенести все виды из предыдущего задания на JSP[#147112]
            users.set(index, user);
        }
        return user != null;
    }

    @Override
    public Boolean delete(User user) {
<<<<<<< HEAD
        User user_ = findById(user.getId());
        if (user_ != null) {
            int index = users.indexOf(user_);
=======
        User user1 = findById(user.getId());
        if (user1 != null) {
            int index = users.indexOf(user1);
>>>>>>> 74a88dd... 1. Перенести все виды из предыдущего задания на JSP[#147112]
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
