package ru.bgbrakhi.carseller.services;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bgbrakhi.carseller.models.CarType;
import ru.bgbrakhi.carseller.models.City;
import ru.bgbrakhi.carseller.repositories.ICarTypeRepository;
import ru.bgbrakhi.carseller.repositories.ICityRepository;

import java.util.List;

@Service()
@Repository
public class CarTypeServiceImpl implements ICarTypeService {

    @Autowired
    private ICarTypeRepository carTypeRepository;

    @Override
    public List<CarType> getAll() {
        return Lists.newArrayList(carTypeRepository.findAll());
    }

    @Override
    public CarType getByName(String name) {
        return carTypeRepository.findByName(name);
    }
}
