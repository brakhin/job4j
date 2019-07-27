package ru.bgbrakhi.servlets.servletsjsp;

import ru.bgbrakhi.servlets.DispatchPattern;
import ru.bgbrakhi.servlets.User;
import ru.bgbrakhi.servlets.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.CopyOnWriteArrayList;

public class UsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("users.jsp").forward(req, resp);
    }
}
