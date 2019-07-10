<%@ page import="ru.bgbrakhi.servlets.ValidateService" %><%--
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
    <title>Create user</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/edit" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
    ID : <%=request.getParameter("id")%><br>
    Name : <input type = "text" name="name" value="<%=ValidateService.getInstance().findById(Integer.parseInt(request.getParameter("id"))).getName()%>">
    <input type = "submit">
</form>
</body>
</html>


