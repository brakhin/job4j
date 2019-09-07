package ru.bgbrakhi.carseller.models;

public class CarData {
    private String login;
    private String cityName;
    private String carType;
    private String carMark;
    private String carModel;
    private String carBody;
    private Integer year;
    private Integer price;
    private String fileName;

    public CarData(String login, String cityName, String carType, String carMark, String carModel, String carBody, Integer year, Integer price, String fileName) {
        this.login = login;
        this.cityName = cityName;
        this.carType = carType;
        this.carMark = carMark;
        this.carModel = carModel;
        this.carBody = carBody;
        this.year = year;
        this.price = price;
        this.fileName = fileName;
    }

    public String getLogin() {
        return login;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCarType() {
        return carType;
    }

    public String getCarMark() {
        return carMark;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCarBody() {
        return carBody;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getPrice() {
        return price;
    }

    public String getFileName() {
        return fileName;
    }
}
