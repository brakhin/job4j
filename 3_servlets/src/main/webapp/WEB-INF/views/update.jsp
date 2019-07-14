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
    <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
    ID : <c:out value="${user.id}"></c:out> <br>
    Name : <input type = "text" name="name" value="<c:out value="${user.name}"></c:out>"><br>
    <input type = "submit">
</form>
</body>
</html>


