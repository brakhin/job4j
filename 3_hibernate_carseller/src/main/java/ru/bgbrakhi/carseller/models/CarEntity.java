package ru.bgbrakhi.carseller.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ref_carentity")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_city")
    private City city;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_carmodel")
    private CarModel carmodel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_carbody")
    private CarBody carbody;

    @Column(name = "year")
    @NotNull
    private Integer year;

    @Column(name = "price")
    @NotNull
    private Integer price;

    @Column(name = "filename")
    private String filename;

    @Column(name = "inactive", columnDefinition = "boolean default false")
    @NotNull
    private Boolean inactive;

    public CarEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public CarModel getCarmodel() {
        return carmodel;
    }

    public void setCarmodel(CarModel carmodel) {
        this.carmodel = carmodel;
    }

    public CarBody getCarbody() {
        return carbody;
    }

    public void setCarbody(CarBody carbody) {
        this.carbody = carbody;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Boolean getInactive() {
        return inactive;
    }

    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarEntity carEntity = (CarEntity) o;
        return id == carEntity.id
                && Objects.equals(carmodel, carEntity.carmodel)
                && Objects.equals(carbody, carEntity.carbody)
                && Objects.equals(year, carEntity.year);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, carmodel, carbody, year);
    }

    @Override
    public String toString() {
        return "CarEntity{"
                + "id=" + id
                + ", carmodel=" + carmodel
                + ", carbody=" + carbody
                + ", year=" + year
                + '}';
    }
}
