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
    function showMyNotes() {
        window.location = "mynotes.do";
    }
    function doLogout() {
        window.location = "login.do?logout";
    }


</script>

</head>
<body>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <!-- Brand -->
    <a class="navbar-brand" id="navbar" href="#">CarStore${login}</a>

    <!-- Links -->
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link"  onclick="showMyNotes();">Мои объявления</a>
        </li>
    </ul>
</nav>

<div class="container-left" style="background-color:lightgray;padding:10px">

    <div class="container" style="padding-top: 20px; padding-bottom: 20px">
        <form action="${pageContext.servletContext.contextPath}/" method="post">

            <div class="form-group">
                <label for="cbMark"><b>Марка</b></label>
                <select class="form-control" id="cbMark" name="mark">
                    <c:forEach items="${marks}" var="mark" varStatus="status">
                        <option>${mark.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group form-check">
                <label class="form-check-label">
                    <input class="form-check-input" type="checkbox" id="today" name="today"><b>За сегодня</b>
                </label>
            </div>
            <div class="form-group form-check">
                <label class="form-check-label">
                    <input class="form-check-input" type="checkbox" id="photoonly" name="withphoto"><b>Только с фото</b>
                </label>
            </div>
            <button type="submit" class="btn btn-primary" style="float:left;
                                       margin-right:10px">Поиск</button>
            <br>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

        </form>
    </div>
</div>

<table class="table table-striped" id="table">
    <thead>
    <tr>
        <th>№</th>
        <th>Дата</th>
        <th>Изображение</th>
        <th>Город</th>
        <th>Тип</th>
        <th>Марка</th>
        <th>Модель</th>
        <th>Кузов</th>
        <th>Год выпуска</th>
        <th>Цена</th>
    </tr>
    </thead>
    <c:set var="index" scope="page" value="0"/>
    <tbody>
    <c:forEach items="${cars}" var="car" varStatus="status">
        <c:set var="index" value="${index + 1}" scope="page"/>
        <jsp:useBean id="dateValue" class="java.util.Date"/>
        <jsp:setProperty name="dateValue" property="time" value="${car.timestamp}"/>
        <tr>
            <td style='width: 2%;vertical-align: middle'>${index}</td>
            <td style='width: 170px;vertical-align: middle'>
                <fmt:formatDate value="${dateValue}" pattern="dd.MM.yyyy HH:mm"/>
            </td>
            <td style='width: 200px;vertical-align: middle'>
                <c:choose>
                    <c:when test="${car.filename.length()==0}">
                        NO IMAGE
                    </c:when>
                    <c:otherwise>
                        <img class='rounded mx-auto d-block' id='picture' name='picture' style='max-width: 400px'
                             src='${pageContext.servletContext.contextPath}/image?file=${car.filename}'>
                    </c:otherwise>
                </c:choose>
            </td>
            <td style='vertical-align: middle'>${car.city.name}</td>
            <td style='vertical-align: middle'>${car.carmodel.cartype.name}</td>
            <td style='vertical-align: middle'>${car.carmodel.carmark.name}</td>
            <td style='vertical-align: middle'>${car.carmodel.name}</td>
            <td style='vertical-align: middle'>${car.carbody.name}</td>
            <td style='vertical-align: middle'>${car.year}</td>
            <td style='vertical-align: middle'>${car.price}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
