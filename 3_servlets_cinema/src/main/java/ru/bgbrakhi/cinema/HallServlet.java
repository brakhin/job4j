package ru.bgbrakhi.cinema;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class HallServlet extends HttpServlet {
    private final Validator logic = Validator.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        List<Seat> seats = logic.getSeats();
        String seatsJSON = new Gson().toJson(seats);
        writer.append(seatsJSON);
        writer.flush();
    }

}
