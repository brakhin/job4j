package ru.bgbrakhi.carseller.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.bgbrakhi.carseller.models.CarBody;
import ru.bgbrakhi.carseller.models.CarMark;

import java.util.List;

public interface ICarBodyRepository extends CrudRepository<CarBody, Integer> {
    @Query("select ce.carbody from Car ce where ce.carmodel.cartype.name = :cartype")
    List<CarBody> getCarBodyByType(@Param("cartype")String cartype);

    CarBody findByName(String name);
}
