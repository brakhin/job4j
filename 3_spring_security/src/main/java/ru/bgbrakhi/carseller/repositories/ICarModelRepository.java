package ru.bgbrakhi.carseller.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.bgbrakhi.carseller.models.CarMark;
import ru.bgbrakhi.carseller.models.CarModel;
import ru.bgbrakhi.carseller.models.CarType;

import java.util.List;

public interface ICarModelRepository extends CrudRepository<CarModel, Integer> {
    List<CarModel> findByCartype_Name(String carType);
    CarModel findByCartypeAndCarmarkAndName(CarType carType, CarMark carMark, String name);
}
