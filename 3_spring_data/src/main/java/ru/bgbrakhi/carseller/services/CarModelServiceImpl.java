package ru.bgbrakhi.carseller.services;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.bgbrakhi.carseller.models.CarModel;
import ru.bgbrakhi.carseller.repositories.ICarModelRepository;
import ru.bgbrakhi.carseller.repositories.ICarRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service()
@Repository
@Transactional
public class CarModelServiceImpl implements ICarModelService {

    @Autowired
    private ICarModelRepository carModelRepository;

    @Override
    public List<CarModel> findForType(String cartype) {
        return cartype.isEmpty()
                ?
                Lists.newArrayList(carModelRepository.findAll())
                :
                carModelRepository.findForType(cartype);
    }
}
