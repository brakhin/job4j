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
        function validate() {
            var login = document.getElementsByName("login")[0].valueOf();
            var password = $('#password').val();

            console.log(login);

            if (login == undefined) {
                alert("Login is empty")
                return false;
            }
            if (password == '') {
                alert(login)
                return false;
            }
            return true;
        }
    </script>
</head>
<body>

<div class="container">
    <c:if test="${error != null}">
        <div class="card" style="background-color: red; color: white; text-transform: uppercase; font-weight: bold">
            &nbsp;<c:out value="${error}"/>
        </div>
    </c:if>
    <h2>Authorization form</h2>
    <form action="${pageContext.servletContext.contextPath}/signin" method="post">
        <div class="form-group">
            <label for="login">Login:</label>
            <input type="text" class="form-control" id="login" placeholder="Enter login" name="login">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
        </div>
        <button type="submit" class="btn btn-primary" onclick="return validate();">Send</button>
    </form>
</div>


</body>
</html>

