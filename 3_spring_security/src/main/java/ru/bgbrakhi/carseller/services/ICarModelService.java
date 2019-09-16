package ru.bgbrakhi.carseller.services;

import ru.bgbrakhi.carseller.models.CarModel;

import java.util.List;

public interface ICarModelService {
    List<CarModel> findForType(String cartype);
}
