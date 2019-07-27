package ru.bgbrakhi.servlets.servletsjsp;

import ru.bgbrakhi.servlets.DispatchPattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        String name = req.getParameter("name");

        if ("update".equals(action)) {
            if ((Boolean) new DispatchPattern(action, name, Integer.parseInt(id)).init().process(action)) {
                resp.sendRedirect(String.format("%s/users.jsp", req.getContextPath()));
            }
        } else {
            RequestDispatcher disp = req.getRequestDispatcher("update.jsp");
            disp.forward(req, resp);
        }
    }
}
