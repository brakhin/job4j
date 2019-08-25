package ru.bgbrakhi.carseller.models;

import com.sun.istack.NotNull;
import ru.bgbrakhi.carseller.service.Storage;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "notice")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_city")
    private City city;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_carentity")
    @NotNull
    private CarEntity carentity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    @NotNull
    private User user;

    @Column(name = "created")
    @NotNull
    private Timestamp created;

    @Column(name = "price")
    @NotNull
    private Float price;

    @Column(name = "active")
    @NotNull
    private Boolean active;

    public Notice() {
        created = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Notice notice = (Notice) o;
        return id == notice.id
                && Objects.equals(carentity, notice.carentity)
                && Objects.equals(user, notice.user)
                && Objects.equals(created, notice.created)
                && Objects.equals(price, notice.price)
                && Objects.equals(active, notice.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carentity, user, created, price, active);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CarEntity getCarentity() {
        return carentity;
    }

    public void setCarentity(CarEntity carentity) {
        this.carentity = carentity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public static void main(String[] args) {
        Notice notice = new Notice();
        List<CarEntity> list = Storage.getInstance().getUserCarEntities("1");
    }


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
