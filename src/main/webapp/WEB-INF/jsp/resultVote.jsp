
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<body style="background-color: #D3D3D3">

<h2>Результаты голосование</h2>
        <h4>Рейтинг исполнителей:</h4>
        <c:forEach var="singer" items="${requestScope.singersort}">
            <span>${singer.key} : ${singer.value}</span>
            <br>
        </c:forEach>

        <br>

        <h4>Рейтинг жанров:</h4>
        <c:forEach var="janre" items="${requestScope.janresort}">
            <span>${janre.key} : ${janre.value}</span>
            <br>
        </c:forEach>
        <br>

        <h4>Информация от пользователей:</h4>

        <c:forEach var="info" items="${requestScope.textsort}">
            <p>
            <span>${info.time}: ${info.textInfo}</span>
            </p>
        </c:forEach>
</body>
