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
    <title>Create user</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script>
        $(
            $.ajax("./json", {
                method: "get",
                complete : function(data) {
                    var cities_list = JSON.parse(data.responseText);
                    var result = "";
                    for (var i = 0; i < cities_list.length; i++) {
                        result += "<option value=\"" + (i + 1) + "\">" + cities_list[i].city + "</option>\n"
                    }
                    var cities_select = document.getElementById("city");
                    cities_select.innerHTML = result;
                }
            })
        )
    </script>
</head>
<body>


<div class="container">
    <h2>Create user</h2>

    <form action="${pageContext.servletContext.contextPath}/create" method="post">
        <div class="form-group">
            <label for="login">Login:</label>
            <input type="text" class="form-control" id="login" placeholder="Enter login"
                   name="login" value="<c:out value="${user.login}"></c:out>">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" placeholder="Enter password"
                   name="password" value="<c:out value="${user.password}"></c:out>">
        </div>

        <label for="city">City:</label>
        <select name="city" id="city" class="custom-select mb-3">
            <option value="1">Moscow</option>
            <option value="2">Krasnodar</option>
            <option value="3">St-Petersburg</option>
        </select>

        <c:if test="${role == 1}">
            <label for="role">Role:</label>
            <select name="role" id="role" class="custom-select mb-3">
                <option value="1" >Admin</option>
                <option value="2" >DBA</option>
                <option value="3" selected>User</option>
            </select>
        </c:if>

        <button type="submit" class="btn btn-primary">Send</button>
    </form>
</div>
</body>
</html>

