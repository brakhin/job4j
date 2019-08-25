package ru.bgbrakhi.carseller.servlets;


import com.google.gson.Gson;
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
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AjaxController extends HttpServlet {
    private final Validator logic = Validator.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String command = req.getParameter("command");
        if ("login".equals(command)) {
            String login = (String) req.getSession().getAttribute("login");
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(String.format("{\"login\":\"%s\"}", login == null ? "\"\"" : login));
            writer.flush();
        } else if ("get_cities".equals(command)) {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            List<City> items = logic.getAllCities();
            String seatsJSON = new Gson().toJson(items);
            writer.append(seatsJSON);
            writer.flush();
        } else if ("get_types".equals(command)) {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            List<CarType> items = logic.getAllCarTypes();
            String seatsJSON = new Gson().toJson(items);
            writer.append(seatsJSON);
            writer.flush();
        } else if ("get_models".equals(command)) {
            String type = req.getParameter("type");
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            List<CarModel> items = logic.getModels(type);
            String seatsJSON = new Gson().toJson(items);
            writer.append(seatsJSON);
            writer.flush();
        } else if ("get_marks".equals(command)) {
            String type = req.getParameter("type");
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            List<CarModel> items = logic.getModels(type);
            String seatsJSON = new Gson().toJson(items.stream()
                    .map(i -> i.getCarmark())
                    .distinct()
                    .collect(Collectors.toList())
            );
            writer.append(seatsJSON);
            writer.flush();
        } else if ("get_bodies".equals(command)) {
            String type = req.getParameter("type");
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            List<CarBody> items = logic.getBodies(type);
            String seatsJSON = new Gson().toJson(items);
            writer.append(seatsJSON);
            writer.flush();
        } else if ("get_all_cars".equals(command)) {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            List<CarEntity> items = logic.getAllCarEntities();
            String seatsJSON = new Gson().toJson(items);
            writer.append(seatsJSON);
            writer.flush();
        } else if ("get_user_cars".equals(command)) {
            HttpSession session = req.getSession();
            String login = (String) session.getAttribute("login");
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            List<CarEntity> items = logic.getUserCarEntities(login);
            String seatsJSON = new Gson().toJson(items);
            writer.append(String.format("{\"data\" : %s, \"login\" : \"%s\"}", seatsJSON, login));
            writer.flush();
        }
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
        if ("login".equals(command)) {
            User user = logic.getUser(map.get("login"), map.get("password"));
            if (user.getId() != 0) {
                HttpSession session = req.getSession();
                session.setAttribute("login", map.get("login"));
            }
        } else if ("registration".equals(command)) {
            User user = logic.getUser(map.get("login"), map.get("password"));
            if (user.getId() == 0) {
                logic.saveUser(user);
            }
            HttpSession session = req.getSession();
            session.setAttribute("login", map.get("login"));
        } else if ("logout".equals(command)) {
            HttpSession session = req.getSession();
            session.invalidate();
        } else if ("change_inactive".equals(command)) {
            String id = map.get("id");
            logic.swapCarEntityInactiveState(Long.parseLong(id));
        } else if ("add_data".equals(command)) {
            HttpSession session = req.getSession();
            CarEntity carEntity = new CarEntity();
            CarModel carModel = new CarModel();
            CarType carType = new CarType();
            carType.setName(map.get("type"));
            CarMark carMark = new CarMark();
            carMark.setName(map.get("mark"));
            City city = new City();
            city.setName(map.get("city"));
            CarBody carBody = new CarBody();
            carBody.setName(map.get("body"));
            carModel.setCartype(carType);
            carModel.setCarmark(carMark);
            carModel.setName(map.get("model"));
            carEntity.setCity(city);
            carEntity.setCarmodel(carModel);
            carEntity.setCarbody(carBody);
            carEntity.setYear(Integer.parseInt(map.get("year")));
            carEntity.setPrice(Integer.parseInt(map.get("price")));
            carEntity.setFilename("null".equals(map.get("file")) ? "" : map.get("file"));
            logic.getCarEntity((String) session.getAttribute("login"), map.get("city"), map.get("type"), map.get("mark"), map.get("model"),
                    map.get("body"), Integer.parseInt(map.get("year")), Integer.parseInt(map.get("price")),
                    "null".equals(map.get("file")) ? "" : map.get("file"));
        }
    }
}
