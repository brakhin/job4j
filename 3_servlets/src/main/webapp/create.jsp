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
        <title>Create user</title>
    </head>
    <body>
        <form action="<%=request.getContextPath()%>/create" method="post">
            Name : <input type = "text" name="name"><br>
            <input type = "submit">
        </form>
    </body>
</html>

