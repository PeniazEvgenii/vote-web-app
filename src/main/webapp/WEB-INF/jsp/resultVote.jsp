
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <title>Результаты голосования</title>
</head>

<body style="background-color: #D3D3D3">

    <%@ include file="header.jsp" %>


    <h2><fmt:message key="page.resultvote.result"/></h2>
    <h4><fmt:message key="page.resulvote.ratingsinger"/>:</h4>
    <table style="width:8%">
        <c:forEach var="singer" items="${requestScope.singersort}">
            <tr>
                <td>${singer.key} :</td>
                <td>${singer.value}</td>
            </tr>
        </c:forEach>

    </table>
    <br>

    <h4><fmt:message key="page.resultvote.ratingjenre"/>:</h4>
    <table style="width:8%">
        <c:forEach var="janre" items="${requestScope.janresort}">
            <tr>
                <td>${janre.key} :</td>
                <td>${janre.value}</td>
            </tr>
        </c:forEach>
    </table>
    <br>

    <h4><fmt:message key="page.resultvote.userinfo"/>:</h4>

    <c:forEach var="info" items="${requestScope.textsort}">
        <p>
        <span>${info.time}: ${info.textInfo}</span>
        </p>
    </c:forEach>
</body>
