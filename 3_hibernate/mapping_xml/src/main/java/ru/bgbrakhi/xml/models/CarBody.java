package ru.bgbrakhi.xml.models;

import org.hibernate.Session;
import ru.bgbrakhi.xml.utils.HibernateUtil;

import java.util.Objects;

public class CarBody {
    private long id;
    private String name;

    public CarBody() {
    }

    public CarBody(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public String toString() {
        return "CarBody{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarBody carBody = (CarBody) o;
        return id == carBody.id
                && Objects.equals(name, carBody.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
