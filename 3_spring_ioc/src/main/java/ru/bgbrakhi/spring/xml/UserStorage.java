package ru.bgbrakhi.spring.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserStorage {
    private static final Logger LOG = LogManager.getLogger(UserStorage.class);
    private final IStorage storage;

    @Autowired
    public UserStorage(IStorage storage) {
        this.storage = storage;
    }

    public Long addUser(User user) {
        return this.storage.addUser(user);
    }

    public void updateUser(Long id, User user) {
        this.storage.updateUser(id, user);
    }

    public void deleteUser(Long id) {
        this.storage.deleteUser(id);
    }

    public User get(Long id) {
        return this.storage.get(id);
    }
}
