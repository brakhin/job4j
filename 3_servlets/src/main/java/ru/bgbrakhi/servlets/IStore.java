package ru.bgbrakhi.servlets;

import java.util.concurrent.CopyOnWriteArrayList;

public interface IStore {
    Boolean add(User user);
    Boolean update(User user);
    Boolean delete(User user);
    User findById(Integer id);
    CopyOnWriteArrayList<User> findAll();
    Integer isCredentional(String login, String password);
}
