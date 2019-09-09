package ru.bgbrakhi.spring;

public interface IStorage {
    Long addUser(User user);
    void updateUser(Long id, User user);
    void deleteUser(Long id);
    User get(Long id);
}
