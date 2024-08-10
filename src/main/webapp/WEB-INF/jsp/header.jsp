<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<body>

    <form action="${pageContext.request.contextPath}/locale" method="post" id="local">
        <button type="submit" name="lang" value="ru_RU">RU</button>
        <button type="submit" name="lang" value="en_EN">EN</button>
    </form>

    <fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'ru_RU'}"/>
    <fmt:setBundle basename="translations"/>

</body>