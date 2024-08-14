
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <title>Результаты голосования</title>
  <style>
    tr:nth-child(even) {
      background-color: #A9A9A9;
    }

    td {
      text-align: left;
      padding: 5px;
    }
    table {
      border-collapse: collapse;
      width: 18%;
    }

    </style>
</head>

<body style="background-color: #D3D3D3">

    <%@ include file="header.jsp" %>


    <h2><fmt:message key="page.resultvote.result"/></h2>
    <h4><fmt:message key="page.resulvote.ratingsinger"/>:</h4>
    <table>
        <c:forEach var="singer" items="${requestScope.singersort}">
            <tr>
                <td>${requestScope.test1[singer.key]} :</td>
                <td>${singer.value}</td>
            </tr>
        </c:forEach>

    </table>
    <br>

    <h4><fmt:message key="page.resultvote.ratingjenre"/>:</h4>
    <table>
        <c:forEach var="janre" items="${requestScope.janresort}">
            <tr>
                <td>${requestScope.test2[janre.key]} :</td>
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
