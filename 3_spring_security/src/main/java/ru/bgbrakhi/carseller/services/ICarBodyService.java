package ru.bgbrakhi.carseller.services;

import org.springframework.data.repository.query.Param;
import ru.bgbrakhi.carseller.models.Car;
import ru.bgbrakhi.carseller.models.CarBody;
import ru.bgbrakhi.carseller.models.CarMark;

import java.util.List;

public interface ICarBodyService {
    List<CarBody> getCarBodyByType(String cartype);
    CarBody getByName(String name);
    CarBody save(CarBody car);

}
