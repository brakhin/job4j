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
        Create user <br>
        <form action="${pageContext.servletContext.contextPath}/create" method="post">
            Name : <input type = "text" name="name"><br>
            <input type = "submit">
        </form>
    </body>
</html>

