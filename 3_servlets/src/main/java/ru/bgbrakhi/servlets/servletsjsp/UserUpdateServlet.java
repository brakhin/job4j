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

public class UserUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ValidateService.getInstance().findById(Integer.parseInt(req.getParameter("id")));
        if (user != null) {
            req.setAttribute("user", user);
            req.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        String name = req.getParameter("name");

        if ("update".equals(action)) {
            if ((Boolean) new DispatchPattern(action, name, Integer.parseInt(id)).init().process(action)) {
//                resp.sendRedirect(String.format("%s/users.jsp", req.getContextPath()));
                req.setAttribute("users", ValidateService.getInstance().findAll());
                req.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(req, resp);
            }
        } else {
            doGet(req, resp);
//            req.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(req, resp);

//            RequestDispatcher disp = req.getRequestDispatcher("update.jsp");
//            disp.forward(req, resp);
        }
    }
}
