package ru.bgbrakhi.servlets.servletsjsp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class JSONController extends HttpServlet {

    private final List<String> cities = new ArrayList<>();


    @Override
    public void init() throws ServletException {
        super.init();
        cities.add("Moscow");
        cities.add("Petersburg");
        cities.add("Krasnodar");
        cities.add("Kazan");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("[{");
        for (int i = 0; i < cities.size(); i++) {
            writer.append(String.format("\"city\":\"%s\"}%s", cities.get(i), i < cities.size() - 1 ? ",{" : ""));
        }
        writer.append("]");
        writer.flush();
    }
}
