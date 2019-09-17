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
    function doLogout() {
//        window.location = "login.do?logout";
        window.location = "logout.do";
    }
    function changeInactive(id, el) {
        if (el.checked) {el.parentElement.parentElement.className = "table-danger"}
        else {el.parentElement.parentElement.className = ""}

        $.ajax("${pageContext.servletContext.contextPath}/ajax", {
            method : "post",
            dataType : "json",
            data : {command: "swap_inactive", data : id},
        });
    }
    function btAddSelClick() {
        var button = document.activeElement;
        var comboBox = document.getElementById("cb" + button.id.toString());
        var edit = document.getElementById("ed" + button.id.toString());

        button.firstChild.data = "qweqwe";
        button.classList.toggle('btn-danger');
        if(parseInt(button.value, 10) == 0) {
            button.innerText = "Выбрать"
            comboBox.hidden = true;
        } else {
            button.innerText = "Добавить"
            comboBox.hidden = false;
        }
        edit.hidden = !comboBox.hidden;
        button.value = (parseInt(button.value, 10) + 1) % 2;
    }
    function validate() {
        var edCity = document.getElementById("edCity");
        var cbCity = document.getElementById("cbCity");
        var edType = document.getElementById("edType");
        var cbType = document.getElementById("cbType");
        var edMark = document.getElementById("edMark");
        var cbMark = document.getElementById("cbMark");
        var edModel = document.getElementById("edModel");
        var cbModel = document.getElementById("cbModel");
        var edBody = document.getElementById("edBody");
        var cbBody = document.getElementById("cbBody");
        var edYear = document.getElementById("edYear");
        var edPrice = document.getElementById("edPrice");

        if (edCity.hidden) edCity.value = cbCity.selectedIndex < 0 ? '' : cbCity.options[cbCity.selectedIndex].value;
        if (edType.hidden) edType.value = cbType.selectedIndex < 0 ? '' : cbType.options[cbType.selectedIndex].value;
        if (edMark.hidden) edMark.value = cbMark.selectedIndex < 0 ? '' : cbMark.options[cbMark.selectedIndex].value;
        if (edModel.hidden) edModel.value = cbModel.selectedIndex < 0 ? '' : cbModel.options[cbModel.selectedIndex].value;
        if (edBody.hidden) edBody.value = cbBody.selectedIndex < 0 ? '' : cbBody.options[cbBody.selectedIndex].value;

        if (edCity.value == '') {
            alert("Не указан город")
            return false;
        }
        if (edType.value == '') {
            alert("Не указан тип")
            return false;
        }
        if (edMark.value == '') {
            alert("Не указана марка")
            return false;
        }
        if (edModel.value == '') {
            alert("Не указана модель ")
            return false;
        }
        if (edBody.value == '') {
            alert("Не указан кузов")
            return false;
        }
        if (edYear.value == '') {
            alert("Не казан год выпуска")
            return false;
        }
        if (edPrice.value == '') {
            alert("Не указана цена")
            return false;
        }
        return true;
    }

    function changeType() {
        var cbType = document.getElementById("cbType");
        if (cbType.selectedIndex > -1) {
            $.ajax("${pageContext.servletContext.contextPath}/ajax", {
                    method : "get",
                    data : {command : "get_models", data : cbType.options[cbType.selectedIndex].text},
                    complete : function(data) {
                        if (data.responseText.length > 0) {
                            var items = JSON.parse(data.responseText);
                            var result = "";
                            for (var i = 0; i < items.length; i ++) {
                                var item = items[i];
                                result += "<option>" + item.name + "</option>\n";
                            }
                            document.getElementById("cbModel").innerHTML = result;
                        }
                    }
                }
            );
            $.ajax("${pageContext.servletContext.contextPath}/ajax", {
                    method : "get",
                    data : {command : "get_marks", data : cbType.options[cbType.selectedIndex].text},
                    complete : function(data) {
                        if (data.responseText.length > 0) {
                            var items = JSON.parse(data.responseText);
                            var result = "";
                            for (var i = 0; i < items.length; i ++) {
                                var item = items[i];
                                result += "<option>" + item.name + "</option>\n";
                            }
                            document.getElementById("cbMark").innerHTML = result;
                        }
                    }
                }
            );
            $.ajax("${pageContext.servletContext.contextPath}/ajax", {
                    method : "get",
                    data : {command : "get_bodies", data : cbType.options[cbType.selectedIndex].text},
                    complete : function(data) {
                        if (data.responseText.length > 0) {
                            var items = JSON.parse(data.responseText);
                            var result = "";
                            for (var i = 0; i < items.length; i ++) {
                                var item = items[i];
                                result += "<option>" + item.name + "</option>\n";
                            }
                            document.getElementById("cbBody").innerHTML = result;
                        }
                    }
                }
            );
        }
    }

</script>
</head>
<body onload="changeType();">

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <!-- Brand -->
    <a class="navbar-brand" id="navbar" href="#">CarStore${login}</a>

    <!-- Links -->
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link"  onclick="showMainPage();">Главная</a>
        </li>
        <li class="nav-item">
            <a class="nav-link"  onclick="doLogout();">Выйти</a>
        </li>
    </ul>
</nav>

<div class="container-left" style="background-color:lightgray;padding:10px">
    <div class="container" style="padding-top: 20px; padding-bottom: 20px">

        <form action="${pageContext.servletContext.contextPath}/mynotes.do" method="post"
              onsubmit="return validate()" enctype="multipart/form-data">

            <table width="100%">
                <tr>
                    <td><b>Город</b></td>
                </tr>
                <tr>
                    <td width="100%">
                        <select class="form-control" id="cbCity" name="cbCity">
                            <c:forEach items="${cities}" var="city" varStatus="status">
                                <option>${city.name}</option>
                            </c:forEach>
                        </select>
                        <input type="text" style="width:100%" id="edCity" name = "edCity" hidden/>
                    </td>
                    <td>
                        <button type="button" class="btn btn-success" id="City" onclick="btAddSelClick();"
                                value="0">Добавить</button>
                    </td>
                </tr>
                <tr>
                    <td><b>Тип</b></td>
                </tr>
                <tr>
                    <td width="100%">
                        <select class="form-control" id="cbType" name="sellist1" onchange="changeType();">
                            <c:forEach items="${types}" var="type" varStatus="status">
                                <option>${type.name}</option>
                            </c:forEach>
                        </select>
                        <input type="text" style="width:100%" id="edType" name = "edType" hidden/>
                    </td>
                    <td>
                        <button type="button" class="btn btn-success" id="Type" onclick="btAddSelClick();"
                                value="0">Добавить</button>
                    </td>
                </tr>
                <tr>
                    <td><b>Марка</b></td>
                </tr>
                <tr>
                    <td width="100%">
                        <select class="form-control" id="cbMark" name="cbMark">
                        </select>
                        <input type="text" style="width:100%" id="edMark" name = "edMark" hidden/>
                    </td>
                    <td  style="width:100px">
                        <button type="button" class="btn btn-success" id="Mark" onclick="btAddSelClick();"
                                value="0">Добавить</button>
                    </td>
                </tr>
                <tr>
                    <td><b>Модель</b></td>
                </tr>
                <tr>
                    <td width="100%">
                        <select class="form-control" id="cbModel" name="cbModel">
                        </select>
                        <input type="text" style="width:100%" id="edModel" name = "edModel" hidden/>
                    </td>
                    <td  style="width:100px">
                        <button type="button" class="btn btn-success" id="Model" onclick="btAddSelClick();"
                                value="0">Добавить</button>
                    </td>
                </tr>
                <tr>
                    <td><b>Кузов</b></td>
                </tr>
                <tr>
                    <td width="100%">
                        <select class="form-control" id="cbBody" name="cbBody">
                        </select>
                        <input type="text" style="width:100%" id="edBody" name = "edBody" hidden/>
                    </td>
                    <td  style="width:100px">
                        <button type="button" class="btn btn-success" id="Body" onclick="btAddSelClick();"
                                value="0">Добавить</button>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table>
                            <tr>
                                <td>
                                    <table>
                                        <tr>
                                            <td><b>Год выпуска</b></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <input type="number" id="edYear" name="edYear">
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                                <td>
                                    <table>
                                        <tr>
                                            <td><b>Цена</b></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <input type="number" id="edPrice" name="edPrice" width="70px">
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <br>
                        <input type="file" id="edFile" name="edFile">
                    </td>
                </tr>
                <tr>
                    <td>
                        <br>
                        <button type="submit" class="btn btn-primary" style="float:left;
                                       margin-right:10px" onclick="return validate();">Добавить</button>
                    </td>
                </tr>
            </table>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

        </form>
    </div>
</div>

<table class="table table-striped" id="table">
    <thead>
    <tr>
        <th>№</th>
        <th>Неактивно</th>
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
        <tr <c:if test="${car.inactive}">class="table-danger"</c:if>>
            <td style='width: 2%;vertical-align: middle'>${index}</td>
            <td class='text-center' style='width: 10px;vertical-align: middle'>
                <input class='form-check-input' <c:if test="${car.inactive}">checked</c:if>
                       type='checkbox' onclick='changeInactive(${car.id}, this)'>
                &nbsp;
            </td>
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
