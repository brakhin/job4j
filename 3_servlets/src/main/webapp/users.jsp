<%@ page import="ru.bgbrakhi.servlets.User" %>
<%@ page import="ru.bgbrakhi.servlets.ValidateService" %>
<%--
Created by IntelliJ IDEA.
  User: bgbrakhi
  Date: 05.07.2019
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Users list</title>
    </head>
    <body>
        Users list <br>
        <table border='1p'>
            <tr>
                <th>Id</th>
                <th>Name</th>
            </tr>
            <% for (User user: ValidateService.getInstance().findAll()) {%>
                <tr>
                    <td><%=user.getId()%></td>
                    <td><%=user.getName()%></td>
                    <td>
                        <form action="<%=request.getContextPath()%>/edit" method="post">
                            <input type="hidden" name="id" value="<%=user.getId()%>"/>
                            <input type="submit" value="Edit">
                        </form>
                    </td>
                    <td>
                        <form action="<%=request.getContextPath()%>/delete" method="post">
                            <input type="hidden" name="id" value="<%=user.getId()%>"/>
                            <input type="submit" value="Delete">
                        </form>
                    </td>
                </tr>
            <%}%>
        </table>
    </body>
</html>

