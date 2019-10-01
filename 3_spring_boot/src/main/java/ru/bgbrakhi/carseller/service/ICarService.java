package ru.bgbrakhi.carseller.service;

import ru.bgbrakhi.carseller.filter.UserFilter;
import ru.bgbrakhi.carseller.models.Car;
import java.util.List;

public interface ICarService {
    List<Car> findWithFilter(UserFilter filter, Boolean onlyActive);
    List<Car> findForUser(String username);
    void swapInactive(Long id, String username);
    Car save(Car car);
}
