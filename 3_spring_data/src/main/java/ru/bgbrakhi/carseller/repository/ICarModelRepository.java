package ru.bgbrakhi.carseller.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.bgbrakhi.carseller.models.CarModel;

import java.util.List;

public interface ICarModelRepository extends CrudRepository<CarModel, Integer> {

    @Query("from CarModel cm where cm.cartype.name = :cartype")
    List<CarModel> findForType(@Param("cartype")String cartype);
}
