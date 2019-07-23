package ru.bgbrakhi.cinema;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class MemoryStore implements IStore {
    private static final MemoryStore INSTANCE = new MemoryStore();
    private CopyOnWriteArrayList<User> users = new CopyOnWriteArrayList<>();
    private LongAdder id = new LongAdder();

    public static MemoryStore getInstance() {
        return INSTANCE;
    }

    @Override
    public Boolean add(User user) {
        id.increment();
        user.setId(id.intValue());
        return users.add(user);
    }

    @Override
    public Boolean update(User user) {
        User user1 = findById(user.getId());
        if (user1 != null) {
            int index = users.indexOf(user1);
            users.set(index, user1);
        }
        return user != null;
    }

    @Override
    public Boolean delete(User user) {
        User user1 = findById(user.getId());
        if (user1 != null) {
            int index = users.indexOf(user1);
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

    @Override
    public Integer isCredentional(String login, String password) {
        return -1;
    }
}
