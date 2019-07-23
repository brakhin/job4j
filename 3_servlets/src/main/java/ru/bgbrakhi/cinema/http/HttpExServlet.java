package ru.bgbrakhi.servlets.http;

import ru.bgbrakhi.servlets.DispatchPattern;
import ru.bgbrakhi.servlets.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpExServlet extends HttpServlet {

    private final DispatchPattern dispatcher = new DispatchPattern().init();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String name = req.getParameter("name");
        dispatcher.process(action, new User(-1, name));
    }
}
