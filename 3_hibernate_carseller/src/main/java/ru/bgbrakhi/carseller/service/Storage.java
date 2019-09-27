package ru.bgbrakhi.carseller.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.bgbrakhi.carseller.UserFilter;
import ru.bgbrakhi.carseller.models.*;

import java.util.List;

public class Storage implements IStorage {

    private SessionFactory factory;
    private static final Storage INSTANCE = new Storage();

    private Storage() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    public static Storage getInstance() {
        return INSTANCE;
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
                    session.createQuery(carType.isEmpty()
                            ?
                            "from CarModel"
                            :
                            String.format("from CarModel cm where cm.cartype.name = '%s'", carType)).list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CarBody> getBodies(String carType) {
        try (Session session = factory.openSession()) {
            List<CarBody> list =
                    session.createQuery(String.format("select ce.carbody from Car ce where ce.carmodel.cartype.name = '%s'", carType)).list();
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

    public List<Car> getAllCars(String filter, Boolean onlyActive) {
        try (Session session = factory.openSession()) {

//            ObjectMapper objectMapper = new ObjectMapper();

            StringBuilder builder = new StringBuilder();
            if (filter != null) {

                ObjectMapper objectMapper = new ObjectMapper();
                UserFilter userFilter = objectMapper.readValue(filter, UserFilter.class);
/*
                UserFilter f = new UserFilter();
                f.setMark(filter.replace("{", "").replace("}", "").split(",")[0].split(":")[1].trim());
                f.setToday(Boolean.parseBoolean(filter.replace("{", "").replace("}", "").split(",")[1].split(":")[1].trim()));
                f.setPhotoOnly(Boolean.parseBoolean(filter.replace("{", "").replace("}", "").split(",")[2].split(":")[1].trim()));
*/
                if (!userFilter.getMark().isEmpty()) {
                    builder.append(String.format(" and ce.carmodel.carmark.name = '%s'", userFilter.getMark()));
                }
                if (userFilter.getToday()) {
                    Long startDayTimestamp = (System.currentTimeMillis() / (60*60*24*1000));
                    startDayTimestamp *= 60*60*24*1000;
                    builder.append(String.format(" and ce.timestamp > %d", startDayTimestamp));
                }
                if (userFilter.getPhotoOnly()) {
                    builder.append(" and coalesce(ce.filename, '') <> ''");
                }
            }
            List<Car> list;
            if (onlyActive) {
                list = session.createQuery(String.format("from Car ce where coalesce(ce.inactive, false) = false %s order by ce.id", builder.toString())).list();

            } else {
                list = session.createQuery(String.format("from Car ce where ce.id > -1 %s order by ce.id", builder.toString())).list();
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Car> getUserCars(String  login) {
        try (Session session = factory.openSession()) {
//            List<Car> list = session.createQuery(String.format("from Car ce inner join ce.user u where u.login = '%s'", login)).list();
            List<Car> list = session.createQuery(String.format("from Car ce where ce.user.login = '%s'", login)).list();
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
//    public Car getCar(String login, String cityName, String carType, String carMark, String carModel, String carBody, Integer year, Integer price, String fileName) {
    public Car getCar(CarData carData) {
        CarModel cm = getCarModel(carData.getCarType(), carData.getCarMark(), carData.getCarModel());
        CarBody cb = getCarBody(carData.getCarBody());
        City city = getCity(carData.getCityName());
        User user = getUser(carData.getLogin());

        Car result;
        try (Session session = factory.openSession()) {
            List<?> list = session.createQuery(
                    String.format("from Car ce inner join ce.carmodel cm inner join cm.cartype ct "
                                    + "inner join cm.carmark cmk inner join ce.carbody cb inner join ce.user u "
                                    + "inner join ce.city c "
                                    + "where lower(u.login) = '%s' and lower(c.name) = '%s' and lower(ct.name) = '%s' "
                                    + "and lower(cmk.name) = '%s' and lower(cm.name) = '%s' and lower(cb.name) = '%s' "
                                    + "and ce.year = %d and ce.price = %d and ce.filename = '%s'",
                            carData.getLogin(), carData.getCityName().toLowerCase(), carData.getCarType().toLowerCase(),
                            carData.getCarMark().toLowerCase(), carData.getCarModel().toLowerCase(),
                            carData.getCarBody().toLowerCase(), carData.getYear(), carData.getPrice(),
                            carData.getFileName())).list();
            if (list.size() > 0) {
                Object[] row = (Object[]) list.get(0);
                result = (Car) row[0];
            } else {
                result = new Car();
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new Car();
        }
        if (result.getId() == 0) {
            result.setUser(user);
            result.setCity(city);
            result.setCarmodel(cm);
            result.setCarbody(cb);
            result.setYear(carData.getYear());
            result.setPrice(carData.getPrice());
            result.setFilename(carData.getFileName());
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
            saveUser(result);
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

    private void saveCarEntity(Car car) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.save(car);
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
    public void swapCarInactiveState(Long id) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Car car = session.get(Car.class, id);
            car.setInactive(!(car.getInactive() == null ? false : car.getInactive()));
            session.update(car);
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
