package ru.bgbrakhi.carseller.service;

import ru.bgbrakhi.carseller.models.Car;
import ru.bgbrakhi.carseller.models.CarType;
import ru.bgbrakhi.carseller.models.User;
import ru.bgbrakhi.carseller.models.*;

import java.util.List;

public class Validator implements IStorage {

    private final IStorage storage = Storage.getInstance();
    private static final Validator INSTANCE = new Validator();

    private Validator() {
    }

    public static Validator getInstance() {
        return INSTANCE;
    }

    @Override
    public Car getCar(CarData carData) {
        return !carData.getLogin().isEmpty() ? storage.getCar(carData) : null;
    }

    @Override
    public User getUser(String login, String password) {
        return storage.getUser(login, password);
    }

    @Override
    public User getUser(String login) {
        return storage.getUser(login);
    }

    @Override
    public void saveUser(User user) {
        storage.saveUser(user);
    }

    @Override
    public List<CarType> getCarTypes() {
        return storage.getCarTypes();
    }

    @Override
    public List<Car> getAllCars(String filter, Boolean onlyActive) {
        return storage.getAllCars(filter, onlyActive);
    }

    @Override
    public List<Car> getUserCars(String login) {
        return storage.getUserCars(login);
    }

    @Override
    public List<City> getAllCities() {
        return storage.getAllCities();
    }

    @Override
    public List<CarType> getAllCarTypes() {
        return storage.getAllCarTypes();
    }

    @Override
    public List<CarModel> getModels(String carType) {
        return storage.getModels(carType);
    }

    @Override
    public List<CarBody> getBodies(String carType) {
        return storage.getBodies(carType);
    }

    @Override
    public void swapCarInactiveState(Long id) {
        storage.swapCarInactiveState(id);
    }
}
