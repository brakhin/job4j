[![Build Status](https://travis-ci.org/brakhin/job4j.svg?branch=master)](https://travis-ci.org/brakhin/job4j)
[![codecov](https://codecov.io/gh/brakhin/job4j/branch/master/graph/badge.svg)](https://codecov.io/gh/brakhin/job4j)

# Репозиторий Бориса Брахина по практическим занятиям курса Job4j  
Ниже находятся наиболее интересные проекты, которые я реализовал во время обучения на курсе Job4j

<b>1. Трекер заявок (JDBC, PostgreSQL)</b>
<br>Постановка задачи:
<br>1. Разработать приложение-хранилище заявок

<br><a href="https://github.com/brakhin/job4j/blob/master/1_chapter_002/src/main/java/ru/bgbrakhi/tracker/StartUI.java">Основной модуль</a>
<br><a href="https://github.com/brakhin/job4j/tree/master/1_chapter_002">Ссылка</a> 
<br>UML-схема :
<br><img src="1_chapter_002/Trackstudio_UML.jpg">

<b>2. Парсер вакансий на sql.ru (Quartz, Jsoup, JDBC, PostgreSQL)</b>

<br>Постановка задачи:
<br>1. Реализовать модуль сборки анализа данных с sql.ru.
<br>2. Система должна использовать Jsoup для парсинга страниц.
<br>3. Система должна запускаться раз в день.
<br>4. Система должна собирать данные только про вакансии Java.
<br>5. Данные должны храниться в базе данных. 
<br>6. Учесть дубликаты. Вакансии с одинаковым именем считаются дубликатами.
<br>7. Учитывать время последнего запуска. если это первый запуск. то нужно собрать все объявления с начало года.
<br>8. В системе не должно быть ввода-вывода информации, все настройки берутся из файла app.properties.   
   
<br><a href="https://github.com/brakhin/job4j/blob/master/2_sql/src/main/java/ru/bgbrakhi/sql/jobparser/SqlRuParser.java">Основной модуль</a>
<br><a href="https://github.com/brakhin/job4j/tree/master/2_sql/src/main/java/ru/bgbrakhi/sql/jobparser">Ссылка</a> 
<br>Скриншот :
<br><img src="2_sql/Screenshot.jpg">

<b>3. Сервис покупки билетов в кинотеатр (PostgreSQL, JDBC, JavaServlet, ApacheTomcat, BootStrap)</b>
<br>Постановка задачи:
<br>Разработать простой веб сайт по покупки билетов в кинотеатр.

<br><a href="https://github.com/brakhin/job4j/tree/master/3_servlets_cinema">Ссылка</a> 
<br>Скриншоты :
<br>Выбор места
<br><img src="3_servlets_cinema/Screenshot1.jpg">
<br>Покупка билета
<br><img src="3_servlets_cinema/Screenshot2.jpg">
<br>Отображение выбранного места как купленное
<br><img src="3_servlets_cinema/Screenshot3.jpg">

<b>4. Создать приложение - список дел (PostgreSQL, JDBC, JavaServlet, ApacheTomcat, BootStrap)</b>
<br>Постановка задачи:
Cоздать простое приложение todolist.
1. веб-приложение должно иметь одну страницу index.html. 
2. все данные на форму загружаються через ajax.
3. данные должны сохраняться через hibernate.

<br><a href="https://github.com/brakhin/job4j/blob/master/2_sql/src/main/java/ru/bgbrakhi/sql/jobparser/SqlRuParser.java">Основной модуль</a>
<br><a href="https://github.com/brakhin/job4j/tree/master/2_sql/src/main/java/ru/bgbrakhi/sql/jobparser">Ссылка</a> 
<br>Скриншот :
<br><img src="2_sql/Screenshot.jpg">
 
