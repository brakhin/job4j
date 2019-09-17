package ru.bgbrakhi.carseller.services;

import ru.bgbrakhi.carseller.models.CarBody;
import ru.bgbrakhi.carseller.models.CarMark;
import ru.bgbrakhi.carseller.models.CarModel;

import java.util.List;

public interface ICarMarkService {
    List<CarMark> findForType(String cartype);
    CarMark getByName(String name);
}
