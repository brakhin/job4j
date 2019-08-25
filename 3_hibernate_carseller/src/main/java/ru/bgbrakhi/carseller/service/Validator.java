package ru.bgbrakhi.carseller.service;

import ru.bgbrakhi.carseller.models.CarEntity;
import ru.bgbrakhi.carseller.models.CarType;
import ru.bgbrakhi.carseller.models.User;
import ru.bgbrakhi.carseller.models.*;

import java.util.List;

public class Validator implements IStorage {

    private final IStorage storage = Storage.getInstance();
    private static Class locker = Storage.class;
    private static Validator instance;

    private Validator() {
    }

    public static Validator getInstance() {
        if (instance == null) {
            synchronized (locker) {
                instance = new Validator();
            }
        }
        return instance;
    }

    @Override
    public CarEntity getCarEntity(String login, String cityName, String carType, String carMark, String carModel, String carBody,
                                  Integer year, Integer price, String fileName) {
        return storage.getCarEntity(login, cityName, carType, carMark, carModel, carBody, year, price, fileName);
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
    public List<CarEntity> getAllCarEntities() {
        return storage.getAllCarEntities();
    }

    @Override
    public List<CarEntity> getUserCarEntities(String login) {
        return storage.getUserCarEntities(login);
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
    public void swapCarEntityInactiveState(Long id) {
        storage.swapCarEntityInactiveState(id);
    }
}
