package ru.bgbrakhi.carseller.service;

import ru.bgbrakhi.carseller.models.User;

public interface IUserService {
    void save(User user);
    User findByUsername(String username);
}
