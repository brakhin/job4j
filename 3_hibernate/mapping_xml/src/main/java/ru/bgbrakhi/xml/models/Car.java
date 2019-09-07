package ru.bgbrakhi.xml.models;

import java.util.Objects;

public class Car {
    long id;
    CarBody carbody;
    Engine engine;
    Transmission transmission;

    public Car() {
    }

    public Car(CarBody carbody, Engine engine, Transmission transmission) {
        this.carbody = carbody;
        this.engine = engine;
        this.transmission = transmission;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CarBody getCarbody() {
        return carbody;
    }

    public void setCarbody(CarBody carbody) {
        this.carbody = carbody;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", carbody=" + carbody
                + ", engine=" + engine
                + ", transmission=" + transmission
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id
                && Objects.equals(carbody, car.carbody)
                && Objects.equals(engine, car.engine)
                && Objects.equals(transmission, car.transmission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carbody, engine, transmission);
    }
}
