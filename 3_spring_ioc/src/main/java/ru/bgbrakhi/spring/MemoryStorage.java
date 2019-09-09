package ru.bgbrakhi.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryStorage implements IStorage {
    private static final Logger LOG = LogManager.getLogger(MemoryStorage.class);
    private final Map<Long, User> map = new HashMap<>();
    private Long counter = 0L;

    @Override
    public Long addUser(User user) {
        user.setId(counter++);
        map.put(user.getId(), user);
        return user.getId();
    }

    @Override
    public void updateUser(Long id, User user) {
        User u = map.get(id);
        if (u != null) {
            user.setId(id);
            map.put(id, user);
        }
    }

    @Override
    public void deleteUser(Long id) {
        map.remove(id);
    }

    @Override
    public User get(Long id) {
        return map.get(id);
    }
}
