<%--
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
    <title>Users list</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h2>Users list</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Password</th>
            <th>City</th>
            <th>Role</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user" >
            <tr>
                <td><c:out value="${user.id}"></c:out></td>
                <td><c:out value="${user.login}"></c:out></td>
                <td><c:out value="${user.password}"></c:out></td>
                <td><c:out value="${user.city}"></c:out></td>
                <td><c:out value="${user.role}"></c:out></td>

                <c:if test="${role == 1 || role == user.role}">
                    <td width="64px">
                        <form action="${pageContext.servletContext.contextPath}/edit" method="post">
                            <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>"/>
                            <input class="btn btn-primary" type="submit" value="Edit">
                        </form>
                    </td>
                    <td width="64px">
                        <form action="${pageContext.servletContext.contextPath}/delete" method="post">
                            <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>"/>
                            <input class="btn btn-primary" type="submit" value="Delete">
                        </form>
                    </td>
                </c:if>

            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${role == 1}">
        <form action="${pageContext.servletContext.contextPath}/create" method="get">
            <input class="btn btn-primary" type="submit" value="Create">
        </form>
        <br>
    </c:if>

    <form action="${pageContext.servletContext.contextPath}/logout" method="post">
        <input class="btn btn-primary" type="submit" value="Logout">
    </form>
</div>

</body>
</html>

