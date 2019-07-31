package ru.bgbrakhi.todolist.servlets;

import com.google.gson.Gson;
import ru.bgbrakhi.todolist.models.Item;
import ru.bgbrakhi.todolist.services.ItemStorage;
import ru.bgbrakhi.todolist.services.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class AjaxController extends HttpServlet {

    private final Validator logic = Validator.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        List<Item> items = logic.getAll();
        String seatsJSON = new Gson().toJson(items);
        writer.append(seatsJSON);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String data = reader.readLine();
        Item item = new Item();

        String type = data.split("&")[0].split("=")[1];
        String value = data.split("&")[1].split("=")[1];

        if ("desc".equals(type)) {
            item.setDescription(value);
            item.setCreated(new Timestamp(System.currentTimeMillis()));
            logic.addItem(item);
        } else if ("click".equals(type)) {
            item = logic.getItem(Long.parseLong(value));
            if (item != null) {
                item.setDone(!item.getDone());
                logic.updateItem(item);
//                resp.setStatus(200);
            }
        }
    }
}
