package ru.bgbrakhi.carseller.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.bgbrakhi.carseller.models.*;

import java.util.List;

public class Storage implements IStorage {

    private static Class locker = Storage.class;
    private SessionFactory factory;
    private static Storage instance;

    private Storage() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    public static Storage getInstance() {
        if (instance == null) {
            synchronized (locker) {
                instance = new Storage();
            }
        }
        return instance;
    }

    public void shutdown() {
        if (factory != null) {
            factory.close();
        }
    }

    public List<City> getAllCities() {
        try (Session session = factory.openSession()) {
            List<City> list = session.createQuery("from City").list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CarModel> getModels(String carType) {
        try (Session session = factory.openSession()) {
            List<CarModel> list =
                    session.createQuery(String.format("from CarModel cm where cm.cartype.name = '%s'", carType)).list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CarBody> getBodies(String carType) {
        try (Session session = factory.openSession()) {
            List<CarBody> list =
                    session.createQuery(String.format("from CarEntity ce where ce.carmodel.cartype.name = '%s'", carType)).list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CarType> getAllCarTypes() {
        try (Session session = factory.openSession()) {
            List<CarType> list = session.createQuery("from CarType").list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private CarType getCarType(String carType) {
        CarType result;
        try (Session session = factory.openSession()) {
            List<CarType> list = session.createQuery(String.format("from CarType ct where lower(ct.name) = '%s'", carType.toLowerCase())).list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new CarType(carType);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new CarType(carType);
        }
        return result;
    }

    private CarMark getCarMark(String carMark) {
        CarMark result;
        try (Session session = factory.openSession()) {
            List<CarMark> list = session.createQuery(String.format("from CarMark cm where lower(cm.name) = '%s'", carMark.toLowerCase())).list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new CarMark(carMark);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new CarMark(carMark);
        }
        return result;
    }

    private CarBody getCarBody(String carBody) {
        CarBody result;
        try (Session session = factory.openSession()) {
            List<CarBody> list = session.createQuery(String.format("from CarBody cb where lower(cb.name) = '%s'", carBody.toLowerCase())).list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new CarBody(carBody);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new CarBody(carBody);
        }
        return result;
    }

    private City getCity(String cityName) {
        City result;
        try (Session session = factory.openSession()) {
            List<City> list = session.createQuery(String.format("from City city where lower(city.name) = '%s'", cityName.toLowerCase())).list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new City(cityName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new City(cityName);
        }
        return result;
    }

    public List<CarEntity> getAllCarEntities() {
        try (Session session = factory.openSession()) {
            List<CarEntity> list = session.createQuery("from CarEntity ce where coalesce(ce.inactive, false) = false").list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CarEntity> getUserCarEntities(String  login) {
        try (Session session = factory.openSession()) {
//            List<CarEntity> list = session.createQuery(String.format("from CarEntity ce inner join ce.user u where u.login = '%s'", login)).list();
            List<CarEntity> list = session.createQuery(String.format("from CarEntity ce where ce.user.login = '%s'", login)).list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private CarModel getCarModel(String carType, String carMark, String carModel) {
        CarType ct = getCarType(carType);
        CarMark cm = getCarMark(carMark);

        CarModel result;
        try (Session session = factory.openSession()) {
            List<?> list = session.createQuery(
                    String.format("from CarModel cm inner join cm.cartype ct inner join cm.carmark cmk "
                                    + "where lower(ct.name) = '%s' and lower(cmk.name) = '%s' and lower(cm.name) = '%s'",
                            carType.toLowerCase(), carMark.toLowerCase(), carModel.toLowerCase())).list();
            if (list.size() > 0) {
                Object[] row = (Object[]) list.get(0);
                result = (CarModel) row[0];
            } else {
                result = new CarModel();
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new CarModel();
        }
        if (result.getId() == 0) {
            result.setCartype(ct);
            result.setCarmark(cm);
            result.setName(carModel);
        }
        return result;
    }

    @Override
    public CarEntity getCarEntity(String login, String cityName, String carType, String carMark, String carModel,
                                  String carBody, Integer year, Integer price, String fileName) {
        CarModel cm = getCarModel(carType, carMark, carModel);
        CarBody cb = getCarBody(carBody);
        City city = getCity(cityName);
        User user = getUser(login);

        CarEntity result;
        try (Session session = factory.openSession()) {
            List<?> list = session.createQuery(
                    String.format("from CarEntity ce inner join ce.carmodel cm inner join cm.cartype ct "
                                    + "inner join cm.carmark cmk inner join ce.carbody cb inner join ce.user u "
                                    + "inner join ce.city c "
                                    + "where lower(u.login) = '%s' and lower(c.name) = '%s' and lower(ct.name) = '%s' "
                                    + "and lower(cmk.name) = '%s' and lower(cm.name) = '%s' and lower(cb.name) = '%s' "
                                    + "and ce.year = %d and ce.price = %d and ce.filename = '%s'",
                            login, cityName.toLowerCase(), carType.toLowerCase(), carMark.toLowerCase(), carModel.toLowerCase(),
                            carBody.toLowerCase(), year, price, fileName)).list();
            if (list.size() > 0) {
                Object[] row = (Object[]) list.get(0);
                result = (CarEntity) row[0];
            } else {
                result = new CarEntity();
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new CarEntity();
        }
        if (result.getId() == 0) {
            result.setUser(user);
            result.setCity(city);
            result.setCarmodel(cm);
            result.setCarbody(cb);
            result.setYear(year);
            result.setPrice(price);
            result.setFilename(fileName);
            saveCarEntity(result);
        }
        return result;
    }

    @Override
    public User getUser(String login, String password) {
        User result;
        try (Session session = factory.openSession()) {
            List<User> list = session.createQuery(
                    String.format("from User u where u.login  = '%s' and u.password = '%s'", login, password)
            ).list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new User();
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new User();
        }
        if (result.getId() == 0) {
            result.setLogin(login);
            result.setPassword(password);
        }
        return result;
    }

    @Override
    public User getUser(String login) {
        User result;
        try (Session session = factory.openSession()) {
            List<User> list = session.createQuery(
                    String.format("from User u where u.login  = '%s'", login)
            ).list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new User();
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new User();
        }
        return result;
    }

    private void saveCarEntity(CarEntity carEntity) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.save(carEntity);
            if (transaction != null) {
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            if (transaction != null) {
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<CarType> getCarTypes() {
        List<CarType> result = null;
        try (Session session = factory.openSession()) {
            result = session.createQuery("from CarType").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void swapCarEntityInactiveState(Long id) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            CarEntity carEntity = session.get(CarEntity.class, id);
            carEntity.setInactive(!(carEntity.getInactive() == null ? false : carEntity.getInactive()));
            session.update(carEntity);
            if (transaction != null) {
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
