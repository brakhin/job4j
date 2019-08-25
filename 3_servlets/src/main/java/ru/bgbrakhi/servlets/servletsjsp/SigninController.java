package ru.bgbrakhi.servlets.servletsjsp;

import ru.bgbrakhi.servlets.User;
import ru.bgbrakhi.servlets.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

public class SigninController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("error", "---");
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
   }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Integer id = ValidateService.getInstance().isCredentional(login, password);

        if (id != -1) {
            HttpSession session = req.getSession();
            synchronized (session) {
                User user = ValidateService.getInstance().findById(new User(id, ""));
                if (user != null) {
                    session.setAttribute("login", login);
                    session.setAttribute("role", user.getRole());
                    resp.sendRedirect(String.format("%s/", req.getContextPath()));
                }
            }
        } else {
            req.setAttribute("error", "Credentional is invalid");
            doGet(req, resp);
        }
    }
}
