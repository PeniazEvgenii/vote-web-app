
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<body style="background-color: #D3D3D3">

<h2>Голосование</h2>
    <form action="${pageContext.request.contextPath}/vote" method="post">
        <h4 style="">Выберите лучшего исполнителя</h4>
        <c:forEach var="singer" items="${requestScope.singers}">
            <label>
            <input type="radio" name="singer" value="${singer}">${singer}
            </label>
            <br>
        </c:forEach>

        <h4>Выберите ваши любимые жанры (не менее 3 вариантов)</h4>
        <c:forEach var="janre" items="${requestScope.janres}">
            <label>
            <input type="checkbox" name="janre" value="${janre}">${janre}
            </label>
            <br>
        </c:forEach>

        <br>

        <label for="infoID"><h4>Введите краткий текст о себе</h4>
            <input type="text" name="info" id="infoID" size="80" required>
        </label>

        <br>
        <br>

        <button type="submit">Отправить</button>
        <br>

        <c:if test="${not empty requestScope.voteErrors}">
            <div style="color: #FF0000">
                <c:forEach var="error" items="${requestScope.voteErrors}">
                    <span>${error.description}</span>
                    <br>
                </c:forEach>
            </div>
        </c:if>
    </form>
<br>
        <c:if test="${not empty requestScope.voteError}">
            <div style="color: #780505">
                <c:forEach var="error" items="${requestScope.voteErrors}">
                    <span>${error}</error>
                    <br>
                </c:forEach>
            </div>
        </c:if>

</body>
