package ru.bgbrakhi.carseller.services;

import ru.bgbrakhi.carseller.UserFilter;
import ru.bgbrakhi.carseller.models.Car;
import ru.bgbrakhi.carseller.models.CarType;
import ru.bgbrakhi.carseller.models.City;

import java.util.List;

public interface ICityService {
    List<City> getAll();
    City getByName(String name);
}
