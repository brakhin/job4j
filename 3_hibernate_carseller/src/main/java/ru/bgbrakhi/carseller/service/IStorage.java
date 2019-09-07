package ru.bgbrakhi.carseller.service;

import ru.bgbrakhi.carseller.models.*;

import java.util.List;

public interface IStorage {
//    Car getCar(String login, String cityName, String carType, String carMark, String carModel, String carBody, Integer year, Integer price, String fileName);
    Car getCar(CarData carData);
    User getUser(String login, String password);
    User getUser(String login);
    void saveUser(User user);
    List<CarType> getCarTypes();
    List<Car> getAllCars(String filter, Boolean onlyActive);
    List<Car> getUserCars(String  login);
    List<City> getAllCities();
    List<CarType> getAllCarTypes();
    List<CarModel> getModels(String carType);
    List<CarBody> getBodies(String carType);
    void swapCarInactiveState(Long id);
}
