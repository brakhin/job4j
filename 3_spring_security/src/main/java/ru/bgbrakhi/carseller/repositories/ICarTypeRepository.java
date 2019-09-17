package ru.bgbrakhi.carseller.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.bgbrakhi.carseller.models.CarMark;
import ru.bgbrakhi.carseller.models.CarType;
import ru.bgbrakhi.carseller.models.City;

public interface ICarTypeRepository extends CrudRepository<CarType, Integer> {
    CarType findByName(String name);
}


