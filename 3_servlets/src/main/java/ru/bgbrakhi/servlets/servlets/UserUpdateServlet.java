package ru.bgbrakhi.servlets.servlets;

import ru.bgbrakhi.servlets.DispatchPattern;
import ru.bgbrakhi.servlets.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = "find";
        String id = req.getParameter("id");
        User user = (User) new DispatchPattern(action, "", Integer.parseInt(id)).init().process(action);
        if (user != null) {
            resp.setContentType("text/html");
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.write("<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "<head>\n"
                    + "    <meta charset=\"UTF-8\">\n"
                    + "    <title>Title</title>\n"
                    + "</head>\n"
                    + "<body>\n"
                    + "<form action=" + req.getContextPath() + "/edit method='post'>\n"
                    + "<input type='hidden' name='action' value='update'/>\n"
                    + "<input type='hidden' name='id' value='" + user.getId() + "'/>\n"
                    + "ID : " + user.getId() + "<br>"
                    + "Name : <input type = 'text' name='name' value='" + user.getName() +"'>"
                    + "<input type = 'submit'>"
                    + "</form>\n"
                    + "</body>\n"
                    + "</html>");
            writer.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        String name = req.getParameter("name");

        if ("update".equals(action)) {
            if ((Boolean) new DispatchPattern(action, name, Integer.parseInt(id)).init().process(action)) {
                resp.sendRedirect(req.getContextPath() + "/list");
            }
        } else {
            doGet(req, resp);
        }
    }
}
