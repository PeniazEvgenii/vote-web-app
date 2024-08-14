
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <title>Голосование</title>
</head>

<body style="background-color: #D3D3D3">

<%@ include file="header.jsp" %>

<h2><fmt:message key="page.vote.header"/></h2>
    <form action="${pageContext.request.contextPath}/vote" method="post">
        <h4 style=""><fmt:message key="page.vote.choose.singer"/></h4>
        <c:forEach var="singer" items="${requestScope.singers}">
            <label for="singerId">
            <input type="radio" name="singer" value="${singer.key}" id="singerId">${singer.value}
            </label>
            <br>
        </c:forEach>
        <h4><fmt:message key="page.vote.choose.ganre"/></h4>
        <c:forEach var="janre" items="${requestScope.janres}">
            <label>
            <input type="checkbox" name="janre" value="${janre.key}">${janre.value}
            </label>
            <br>
        </c:forEach>

        <br>

        <label for="infoID"><h4><fmt:message key="page.vote.shortinfo"/></h4>
            <input type="text" name="info" id="infoID" size="80" required>
        </label>

        <br>
        <br>

        <button type="submit"><fmt:message key="button.send" /></button>
        <br>

        <c:if test="${not empty requestScope.voteErrors}">
            <div style="color: #FF0000">
                <c:forEach var="error" items="${requestScope.voteErrors}">
<%--                      <span>${error.description}</span> --%>
                     <span>${sessionScope.lang != null ? (sessionScope.lang == 'ru_RU' ? error.rusDescription : error.description) : error.rusDescription}</span>
                    <br>
                </c:forEach>
            </div>
        </c:if>
    </form>

</body>
