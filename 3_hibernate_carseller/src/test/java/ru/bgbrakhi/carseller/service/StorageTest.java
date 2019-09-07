package ru.bgbrakhi.carseller.service;

import org.junit.BeforeClass;
import org.junit.Test;
import ru.bgbrakhi.carseller.models.*;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StorageTest {
    private static final IStorage storage = Storage.getInstance();

    @BeforeClass
    public static void initData() {
        storage.getUser("user_1", "password_1");
        storage.getUser("user_2", "password_2");
        storage.getCar(new CarData("user_1", "Moscow", "Passenger", "Mazda", "Rx8",
                "Sedan", 1998, 200000, "Image_1.jpg"));
        storage.getCar(new CarData("user_2", "Krasnodar", "Passenger", "Mitsubishi",
                "LancerX", "Sedan", 2008, 300000, "Image_2.jpg"));
        storage.getCar(new CarData("user_1", "St-Petersburg", "Cargo", "Scania",
                "147", "Vagon", 2000, 1200000, ""));
    }

    @Test
    public void getAllCities() {
        List<City> data = storage.getAllCities();
        assertThat(data.size(), is(3));
        assertThat(data.get(1).getName(), is("Krasnodar"));
    }

    @Test
    public void getModels() {
        List<CarModel> data = storage.getModels("Passenger");
        assertThat(data.size(), is(2));
        assertThat(data.get(1).getName(), is("LancerX"));
    }

    @Test
    public void getBodies() {
        List<CarBody> data = storage.getBodies("Passenger");
        assertThat(data.size(), is(2));
        assertThat(data.get(1).getName(), is("Sedan"));
    }

    @Test
    public void getAllCarTypes() {
        List<CarType> data = storage.getAllCarTypes();
        assertThat(data.size(), is(2));
        assertThat(data.get(1).getName(), is("Cargo"));
    }

    @Test
    public void getAllCars() {
        List<Car> data = storage.getAllCars(null, false);
        assertThat(data.size(), is(3));
        assertThat(data.get(1).getCarmodel().getCarmark().getName(), is("Mitsubishi"));
    }

    @Test
    public void getAllCarsFiltered() {
        List<Car> data = storage.getAllCars(
                "{\"mark\" : \"Mitsubishi\", \"today\" : true, \"photoOnly\" : true}", true);
        assertThat(data.size(), is(1));
        assertThat(data.get(0).getCarmodel().getCarmark().getName(), is("Mitsubishi"));
    }

    @Test
    public void getUserCarEntities() {
        List<Car> data = storage.getUserCars("user_2");
        assertThat(data.size(), is(1));
        assertThat(data.get(0).getCarmodel().getCarmark().getName(), is("Mitsubishi"));
    }

    @Test
    public void getCar() {
        Car car = storage.getCar(
                new CarData("user_2", "Krasnodar", "Passenger", "Mitsubishi",
                        "LancerX", "Sedan", 2008, 300000, "Image_2.jpg"));
        assertThat(car.getCarmodel().getCarmark().getName(), is("Mitsubishi"));
        assertThat(car.getFilename(), is("Image_2.jpg"));
    }

    @Test
    public void getUser() {
        User user = storage.getUser("user_1", "password_1");
        assertThat(user.getLogin(), is("user_1"));
        assertThat(user.getPassword(), is("password_1"));
    }

    @Test
    public void getCarTypes() {
        List<CarType> data = storage.getCarTypes();
        assertThat(data.size(), is(2));
        assertThat(data.get(0).getName(), is("Passenger"));
        assertThat(data.get(1).getName(), is("Cargo"));
    }

    @Test
    public void swapCarEntityInactiveState() {
        Car car = storage.getCar(new CarData("user_2", "Krasnodar", "Passenger", "Mitsubishi",
                "LancerX", "Sedan", 2008, 300000, "Image_2.jpg"));
        Boolean before = car.getInactive();
        storage.swapCarInactiveState(car.getId());
        car = storage.getCar(new CarData("user_2", "Krasnodar", "Passenger", "Mitsubishi",
                "LancerX", "Sedan", 2008, 300000, "Image_2.jpg"));
        Boolean after = car.getInactive();
        assertThat(after, is(!before));
    }
}