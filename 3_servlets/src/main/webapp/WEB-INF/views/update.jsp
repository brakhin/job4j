<%@ page import="ru.bgbrakhi.servlets.ValidateService" %>
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
    <meta charset="UTF-8">
    <title>Create user</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/edit" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
    ID : <c:out value="${user.id}"></c:out> <br>
    Login : <input type = "text" name="login" value="<c:out value="${user.login}"></c:out>"><br>
    Password : <input type = "password" name="password" value="<c:out value="${user.password}"></c:out>"><br>
    <c:if test="${role == 1}">
        Role : <select name="role">
            <option value="1" <c:if test="${user.role == 1}">selected</c:if>>Admin</option>
            <option value="2" <c:if test="${user.role == 2}">selected</c:if>>DBA</option>
            <option value="3" <c:if test="${user.role == 3}">selected</c:if>>User</option>
        </select>
    </c:if>
    <c:if test="${role != 1}">
        <input type="hidden" name="role" value="<c:out value="${user.role}"></c:out>">
        Role :
        <c:if test="${user.role == 1}">Admin</c:if>
        <c:if test="${user.role == 2}">DBA</c:if>
        <c:if test="${user.role == 3}">User</c:if>
    </c:if>
    <br>
    <input type = "submit">
</form>
</body>
</html>


