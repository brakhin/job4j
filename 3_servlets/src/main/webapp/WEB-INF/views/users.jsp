<%@ page import="ru.bgbrakhi.servlets.User" %>
<%@ page import="ru.bgbrakhi.servlets.ValidateService" %><%--
  Created by IntelliJ IDEA.
  User: bgbrakhi
  Date: 05.07.2019
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Users list</title>
    </head>
    <body>
        Users list <br>
        <table border="1p">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Password</th>
                <th>Role</th>
            </tr>
            <c:forEach items="${users}" var="user" >
                <tr>
                    <td><c:out value="${user.id}"></c:out></td>
                    <td><c:out value="${user.login}"></c:out></td>
                    <td><c:out value="${user.password}"></c:out></td>
                    <td><c:out value="${user.role}"></c:out></td>

                    <c:if test="${role == 1 || role == user.role}">
                        <td>
                            <form action="${pageContext.servletContext.contextPath}/edit" method="post">
                                <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>"/>
                                <input type="submit" value="Edit">
                            </form>
                        </td>
                        <td>
                            <form action="${pageContext.servletContext.contextPath}/delete" method="post">
                                <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>"/>
                                <input type="submit" value="Delete">
                            </form>
                        </td>
                    </c:if>

                </tr>
            </c:forEach>
        </table>

        <c:if test="${role == 1}">
            <form action="${pageContext.servletContext.contextPath}/create" method="get">
                <input type="submit" value="Create">
            </form>
        </c:if>

        <form action="${pageContext.servletContext.contextPath}/logout" method="post">
            <input type="submit" value="Logout">
        </form>
    </body>
</html>

