package ru.bgbrakhi.carseller.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.bgbrakhi.carseller.models.CarBody;
import ru.bgbrakhi.carseller.models.CarMark;
import ru.bgbrakhi.carseller.models.CarModel;

import java.util.List;

public interface ICarMarkRepository extends CrudRepository<CarMark, Integer> {
    CarMark findByName(String name);
}
