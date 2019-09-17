<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" pageEncoding="UTF-8" session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<title>Список автомобилей</title>
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>


<script>
    function showMainPage() {
        window.location = "${pageContext.servletContext.contextPath}/";
    }

    function validate() {
        if (document.getElementById("login").value == '') {
            alert("Логин не может быть пустым")
            return false;
        }
        if (document.getElementById("password").value == '') {
            alert("Пароль не может быть пустым")
            return false;
        }
        return true;
    }

    function doCancel() {
        window.location = "cars.do";
    }

    function doRegistration() {
        window.location = "registration.html";
    }

</script>


</head>

<body onload="document.loginForm.login.focus();">

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <!-- Brand -->
    <a class="navbar-brand" id="navbar" href="#">CarStore</a>

    <!-- Links -->
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link"  onclick="showMainPage();">Главная</a>
        </li>
    </ul>
</nav>


<div class="container">
    <h2>Авторизация</h2>
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <c:if test="${not empty msg}">
        <div class="msg">${msg}</div>
    </c:if>
    <form name="loginForm" action="<c:url value="/login.do"/>" method="post" onsubmit="return validate()">
        <div class="form-group">
            <label for="login">Логин:</label>
            <input type="text" class="form-control" id="login" placeholder="Введите логин" name="login">
        </div>
        <div class="form-group">
            <label for="password">Пароль:</label>
            <input type="password" class="form-control" id="password" placeholder="Введите пароль" name="password">
        </div>

        <button type="submit" value="submit" class="btn btn-primary"
                style="float:left; margin-right:10px">Принять</button>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
</div>

</body>
</html>