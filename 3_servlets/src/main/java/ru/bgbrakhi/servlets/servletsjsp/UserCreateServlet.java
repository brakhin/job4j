package ru.bgbrakhi.servlets.servletsjsp;

import ru.bgbrakhi.servlets.DispatchPattern;
import ru.bgbrakhi.servlets.User;
import ru.bgbrakhi.servlets.ValidateService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserCreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = "add";
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String city = req.getParameter("city");
        String role = req.getParameter("role");
        new DispatchPattern().init().process(action, new User(-1, login, password,  Integer.parseInt(city), Integer.parseInt(role)));
//        resp.sendRedirect(String.format("%s/create.jsp", req.getContextPath()));

        req.setAttribute("users", ValidateService.getInstance().findAll());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/users.jsp");
        if (dispatcher != null) {
            dispatcher.forward(req, resp);
        }
    }
}
