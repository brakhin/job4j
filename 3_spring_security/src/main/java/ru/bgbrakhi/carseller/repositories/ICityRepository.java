package ru.bgbrakhi.carseller.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.bgbrakhi.carseller.models.Car;
import ru.bgbrakhi.carseller.models.CarType;
import ru.bgbrakhi.carseller.models.City;

import java.util.List;

public interface ICityRepository extends CrudRepository<City, Integer> {
    City findByName(String name);
}


