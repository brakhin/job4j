package ru.bgbrakhi.carseller.service;

import ru.bgbrakhi.carseller.models.CarModel;

import java.util.List;

public interface ICarModelService {
    List<CarModel> findForType(String cartype);
}
