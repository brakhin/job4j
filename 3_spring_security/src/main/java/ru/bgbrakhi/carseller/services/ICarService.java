package ru.bgbrakhi.carseller.services;

import org.springframework.data.repository.query.Param;
import ru.bgbrakhi.carseller.UserFilter;
import ru.bgbrakhi.carseller.models.Car;

import java.util.List;

public interface ICarService {
    List<Car> findWithFilter(UserFilter filter, Boolean onlyActive);
    List<Car> findForUser(String login);
    void swapInactive(Long id, String login);
    Car save(Car car);
}
