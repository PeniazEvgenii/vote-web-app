
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <title>Добавление исполнителей</title>
</head>

<body style="background-color: #D3D3D3">

    <%@ include file="header.jsp" %>


    <h4><fmt:message key="page.artist.artists"/></h4>
        <c:forEach var="singer" items="${requestScope.artist}">
            <span>${singer.value}</span>
            <br>
        </c:forEach>

    <form action="${pageContext.request.contextPath}/service/artist" method="post">
        <h4><fmt:message key="page.artist.addArtist"/></h4>

        <label for="artistID">
            <input type="text" name="artist" id="artistID" size="80" required>
        </label>
        <br>
        <br>
        <button type="submit"><fmt:message key="button.send" /></button>
        <br><br>

        <c:if test="${not empty requestScope.artistErr}">
            <div style="color: #FF0000">
            <span>${requestScope.artistErr}</span>
            </div>
        </c:if>
        <c:if test="${not empty requestScope.artistAdd}">
            <div style="color: #228B22">
            <span>${requestScope.artistAdd}</span>
            </div>
        </c:if>
    </form>

</body>