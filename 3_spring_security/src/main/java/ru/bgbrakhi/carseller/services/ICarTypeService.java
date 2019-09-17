package ru.bgbrakhi.carseller.services;

import ru.bgbrakhi.carseller.models.CarMark;
import ru.bgbrakhi.carseller.models.CarType;
import ru.bgbrakhi.carseller.models.City;

import java.util.List;

public interface ICarTypeService {
    List<CarType> getAll();
    CarType getByName(String name);
}
