package ru.bgbrakhi.annotation.models;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import ru.bgbrakhi.xml.utils.HibernateUtil;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CarTest {

    @Test
    public void ifAddCarThenReadItFromBase() {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            Car car = new Car(new CarBody("CarBody"), new Engine("Engine"), new Transmission("Transmission"));

            session.save(car);
            transaction.commit();

            car = session.get(Car.class, car.getId());
            assertThat(car.toString(), is(
                    "Car{id=1, carbody=CarBody{id=1, name='CarBody'}, engine=Engine{id=1, "
                            + "name='Engine'}, transmission=Transmission{id=1, name='Transmission'}}"
            ));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Test
    public void ifUpdateCarThenReadItFromBase()  {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            Car car = session.get(Car.class, 1L);
            car.setTransmission(new Transmission("Other transmission"));
            session.update(car);
            transaction.commit();
            car = session.get(Car.class, car.getId());

            assertThat(car.toString(), is(
                    "Car{id=1, carbody=CarBody{id=1, name='CarBody'}, engine=Engine{id=1, "
                            + "name='Engine'}, transmission=Transmission{id=" + car.getTransmission().getId() + ", name='Other transmission'}}"
            ));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Test
    public void ifDeleteCarThenReadNullFromBase()  {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            Car car = session.get(Car.class, 1L);
            session.delete(car);
            transaction.commit();
            car = session.get(Car.class, car.getId());

            assertNull(car);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}