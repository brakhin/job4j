package ru.bgbrakhi.carseller.services;

import ru.bgbrakhi.carseller.models.City;
import ru.bgbrakhi.carseller.models.User;

import java.util.List;

public interface IUserService {
    User findByLogin(String login);
}
