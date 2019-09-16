package ru.bgbrakhi.carseller.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.bgbrakhi.carseller.UserFilter;
import ru.bgbrakhi.carseller.models.Car;
import ru.bgbrakhi.carseller.repositories.ICarRepository;

import javax.transaction.Transactional;
import java.util.List;

import static ru.bgbrakhi.carseller.controller.CarsController.COANTANT_FILTER_ALL_MARKS;

@Service()
@Repository
@Transactional
public class CarServiceImpl implements ICarService {

    @Autowired
    private ICarRepository carRepository;

    @Override
    public List<Car> findWithFilter(UserFilter filter, Boolean onlyActive) {
        return carRepository.findWithFilter(!onlyActive,
                filter != null
                        && !COANTANT_FILTER_ALL_MARKS.equals(filter.getMark())
                        && !filter.getMark().isEmpty(),
                filter == null ? "" : filter.getMark(),
                filter != null && filter.getToday(),
                filter != null && filter.getWithphoto());
    }
}
