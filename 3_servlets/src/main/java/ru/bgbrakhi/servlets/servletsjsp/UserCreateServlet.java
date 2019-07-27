package ru.bgbrakhi.servlets.servletsjsp;

import ru.bgbrakhi.servlets.DispatchPattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserCreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = "add";
        String name = req.getParameter("name");
        new DispatchPattern(action, name, -1).init().process(action);
        resp.sendRedirect(String.format("%s/create.jsp", req.getContextPath()));
    }
}
