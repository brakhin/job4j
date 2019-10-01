package ru.bgbrakhi.carseller.service;

import org.junit.BeforeClass;
import org.junit.Test;
import ru.bgbrakhi.carseller.models.*;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StorageTest {
    private static final IStorage STORAGE = Storage.getInstance();

    @BeforeClass
    public static void initData() {
        STORAGE.getUser("user_1", "password_1");
        STORAGE.getUser("user_2", "password_2");
        STORAGE.getCar(new CarData("user_1", "Moscow", "Passenger", "Mazda", "Rx8",
                "Sedan", 1998, 200000, "Image_1.jpg"));
        STORAGE.getCar(new CarData("user_2", "Krasnodar", "Passenger", "Mitsubishi",
                "LancerX", "Sedan", 2008, 300000, "Image_2.jpg"));
        STORAGE.getCar(new CarData("user_1", "St-Petersburg", "Cargo", "Scania",
                "147", "Vagon", 2000, 1200000, ""));
    }

    @Test
    public void getAllCities() {
        List<City> data = STORAGE.getAllCities();
        assertThat(data.size(), is(3));
        assertThat(data.get(1).getName(), is("Krasnodar"));
    }

    @Test
    public void getModels() {
        List<CarModel> data = STORAGE.getModels("Passenger");
        assertThat(data.size(), is(2));
        assertThat(data.get(1).getName(), is("LancerX"));
    }

    @Test
    public void getBodies() {
        List<CarBody> data = STORAGE.getBodies("Passenger");
        assertThat(data.size(), is(2));
        assertThat(data.get(1).getName(), is("Sedan"));
    }

    @Test
    public void getAllCarTypes() {
        List<CarType> data = STORAGE.getAllCarTypes();
        assertThat(data.size(), is(2));
        assertThat(data.get(1).getName(), is("Cargo"));
    }

    @Test
    public void getAllCars() {
        List<Car> data = STORAGE.getAllCars(null, false);
        assertThat(data.size(), is(3));
        assertThat(data.get(1).getCarmodel().getCarmark().getName(), is("Mitsubishi"));
    }

    @Test
    public void getAllCarsFiltered() {
        List<Car> data = STORAGE.getAllCars(
                "{\"mark\" : \"Mitsubishi\", \"today\" : true, \"photoOnly\" : true}", true);
        assertThat(data.size(), is(1));
        assertThat(data.get(0).getCarmodel().getCarmark().getName(), is("Mitsubishi"));
    }

    @Test
    public void getUserCarEntities() {
        List<Car> data = STORAGE.getUserCars("user_2");
        assertThat(data.size(), is(1));
        assertThat(data.get(0).getCarmodel().getCarmark().getName(), is("Mitsubishi"));
    }

    @Test
    public void getCar() {
        Car car = STORAGE.getCar(
                new CarData("user_2", "Krasnodar", "Passenger", "Mitsubishi",
                        "LancerX", "Sedan", 2008, 300000, "Image_2.jpg"));
        assertThat(car.getCarmodel().getCarmark().getName(), is("Mitsubishi"));
        assertThat(car.getFilename(), is("Image_2.jpg"));
    }

    @Test
    public void getUser() {
        User user = STORAGE.getUser("user_1", "password_1");
        assertThat(user.getLogin(), is("user_1"));
        assertThat(user.getPassword(), is("password_1"));
    }

    @Test
    public void getCarTypes() {
        List<CarType> data = STORAGE.getCarTypes();
        assertThat(data.size(), is(2));
        assertThat(data.get(0).getName(), is("Passenger"));
        assertThat(data.get(1).getName(), is("Cargo"));
    }

    @Test
    public void swapCarEntityInactiveState() {
        Car car = STORAGE.getCar(new CarData("user_2", "Krasnodar", "Passenger", "Mitsubishi",
                "LancerX", "Sedan", 2008, 300000, "Image_2.jpg"));
        Boolean before = car.getInactive();
        STORAGE.swapCarInactiveState(car.getId());
        car = STORAGE.getCar(new CarData("user_2", "Krasnodar", "Passenger", "Mitsubishi",
                "LancerX", "Sedan", 2008, 300000, "Image_2.jpg"));
        Boolean after = car.getInactive();
        assertThat(after, is(!before));
    }
}