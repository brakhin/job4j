package ru.bgbrakhi.carseller.servlets;


import com.google.gson.Gson;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.bgbrakhi.carseller.models.*;
import ru.bgbrakhi.carseller.service.Validator;
import ru.bgbrakhi.carseller.utils.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AjaxController extends HttpServlet {
    private final Validator logic = Validator.getInstance();
    
    private static final String CONSTANT_LOGIN = "login";
    private static final String CONSTANT_GET_CITIES = "get_cities";
    private static final String CONSTANT_GET_TYPES = "get_types";
    private static final String CONSTANT_GET_MODELS = "get_models";
    private static final String CONSTANT_GET_MARKS = "get_marks";
    private static final String CONSTANT_GET_BODIES = "get_bodies";
    private static final String CONSTANT_GET_ALL_CARS = "get_all_cars";
    private static final String CONSTANT_GET_USER_CARS = "get_user_cars";
    private static final String CONSTANT_REGISTRATION = "registration";
    private static final String CONSTANT_LOGOUT = "logout";
    private static final String CONSTANT_CHANGE_INACTIVE = "change_inactive";

    private String getLogin(HttpSession session) {
        String login = (String) session.getAttribute("login");
        return String.format("{\"login\":\"%s\"}", login == null ? "''" : login);
    }

    private String getCities() {
        List<City> items = logic.getAllCities();
        return new Gson().toJson(items);
    }

    private String getTypes() {
        List<CarType> items = logic.getAllCarTypes();
        return new Gson().toJson(items);
    }

    private String getModels(String type) {
        List<CarModel> items = logic.getModels(type);
        return new Gson().toJson(items);
    }

    private String getMarks(String type) {
        List<CarModel> items = logic.getModels(type);
        return new Gson().toJson(items.stream()
                .map(i -> i.getCarmark())
                .distinct()
                .collect(Collectors.toList())
        );
    }

    private String getBodies(String type) {
        List<CarBody> items = logic.getBodies(type);
        return new Gson().toJson(items);
    }

    private String getAllCars(String filter) {
        List<Car> items = logic.getAllCars(filter, true);
        return new Gson().toJson(items);
    }

    private String getUserCars(HttpSession session) {
        String login = (String) session.getAttribute("login");
        List<Car> items = logic.getUserCars(login);
        String seatsJSON = new Gson().toJson(items);
        return String.format("{\"data\" : %s, \"login\" : \"%s\"}", seatsJSON, login);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");
//        PrintWriter writer = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF8"), true);

        PrintWriter writer = resp.getWriter();

//        resp.setContentType("text/html");
//        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        String command = req.getParameter("command");
        if (CONSTANT_LOGIN.equals(command)) {
            writer.append(getLogin(req.getSession()));
        } else if (CONSTANT_GET_CITIES.equals(command)) {
            writer.append(getCities());
        } else if (CONSTANT_GET_TYPES.equals(command)) {
            writer.append(getTypes());
        } else if (CONSTANT_GET_MODELS.equals(command)) {
            writer.append(getModels(req.getParameter("type")));
        } else if (CONSTANT_GET_MARKS.equals(command)) {
            writer.append(getMarks(req.getParameter("type")));
        } else if (CONSTANT_GET_BODIES.equals(command)) {
            writer.append(getBodies(req.getParameter("type")));
        } else if (CONSTANT_GET_ALL_CARS.equals(command)) {
            writer.append(getAllCars(req.getParameter("filter")));
        } else if (CONSTANT_GET_USER_CARS.equals(command)) {
            writer.append(getUserCars(req.getSession()));
        }
        writer.flush();
    }

    private void processLogin(HttpSession session, Map<String, String> map) {
        User user = logic.getUser(map.get("login"));
        if (BCrypt.checkpw(map.get("password"), user.getPassword()) && user.getId() != 0) {
            session.setAttribute("login", map.get("login"));
        }
    }

    private void processRegistration(HttpSession session, Map<String, String> map) {
        User user = logic.getUser(map.get("login"), map.get("password"));
        if (user.getId() == 0) {
            logic.saveUser(user);
        }
        session.setAttribute("login", map.get("login"));
    }

    private void processLogount(HttpSession session) {
        session.invalidate();
    }

    private void swapCarInactiveState(Map<String, String> map) {
        String id = map.get("id");
        logic.swapCarInactiveState(Long.parseLong(id));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = new BufferedReader(req.getReader());
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        Map<String, String> map = Util.postDataToMap(builder.toString());
        String command = map.get("command");
        HttpSession session = req.getSession();
        if (CONSTANT_LOGIN.equals(command)) {
            processLogin(session, map);
        } else if (CONSTANT_REGISTRATION.equals(command)) {
            processRegistration(session, map);
        } else if (CONSTANT_LOGOUT.equals(command)) {
            processLogount(session);
        } else if (CONSTANT_CHANGE_INACTIVE.equals(command)) {
            swapCarInactiveState(map);
        }
    }
}
