package ru.bgbrakhi.carseller.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.bgbrakhi.carseller.models.CarBody;
import ru.bgbrakhi.carseller.models.User;

import java.util.List;

public interface IUserRepository extends CrudRepository<User, Integer> {
    User findByLogin(String login);
}
