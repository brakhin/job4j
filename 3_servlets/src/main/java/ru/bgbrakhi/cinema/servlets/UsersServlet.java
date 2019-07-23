package ru.bgbrakhi.servlets.servlets;

import ru.bgbrakhi.servlets.DispatchPattern;
import ru.bgbrakhi.servlets.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.CopyOnWriteArrayList;

public class UsersServlet extends HttpServlet {
    private  final  DispatchPattern dispatcher = new DispatchPattern().init();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        String action = "all";
        CopyOnWriteArrayList<User> users =
                (CopyOnWriteArrayList<User>) dispatcher.process(action, new User(-1, ""));

        StringBuilder sb = new StringBuilder("<table border='1'>");
        sb.append("<tr><td>Id</td><td>Name</td></tr>");
        for (User user : users) {
            sb.append("<tr>\n"
                    + "    <td>" + user.getId() + "</td>\n"
                    + "    <td>" + user.getName() + "</td>\n"
                    + "    <td>\n"
                    + "        <form action=" + req.getContextPath() + "/edit method='post'>\n"
                    + "            <input type='hidden' name='id' value='" + user.getId() + "'/>\n"
                    + "            <input type='submit' value='Edit'>\n"
                    + "        </form>\n"
                    + "    </td>\n"
                    + "    <td>\n"
                    + "        <form action=" + req.getContextPath() + "/delete method='post'>\n"
                    + "            <input type='hidden' name='id' value='" + user.getId() + "'/>\n"
                    + "            <input type='button' value='Delete'>\n"
                    + "        </form>\n"
                    + "    </td>\n"
                    + "</tr>");
        }
        sb.append("</table>");

        writer.append("<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title>Title</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "Users list : <br>"
                + sb.toString()
                + "</body>\n"
                + "</html>");
        writer.flush();

        writer.flush();
    }

}
