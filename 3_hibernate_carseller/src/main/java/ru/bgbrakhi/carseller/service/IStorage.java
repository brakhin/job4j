package ru.bgbrakhi.carseller.service;

import ru.bgbrakhi.carseller.models.*;

import java.util.List;

public interface IStorage {
    CarEntity getCarEntity(String login, String cityName, String carType, String carMark, String carModel,
                            String carBody, Integer year, Integer price, String fileName);
    User getUser(String login, String password);
    User getUser(String login);
    void saveUser(User user);
    List<CarType> getCarTypes();
    List<CarEntity> getAllCarEntities();
    List<CarEntity> getUserCarEntities(String  login);
    List<City> getAllCities();
    List<CarType> getAllCarTypes();
    List<CarModel> getModels(String carType);
    List<CarBody> getBodies(String carType);
    void swapCarEntityInactiveState(Long id);
}
