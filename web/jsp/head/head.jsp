<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="bundle"/>
<c:set var="path" value="${pageContext.request.contextPath}" scope="request"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gum</title>
    <link rel="shortcut icon" href="/image/favicon.png" type="image/png"/>
    <style>
        <c:import url="/css/sidebar.css"/>
        <c:import url="/css/body.css"/>
        <c:import url="/css/footer.css"/>
        <c:import url="/css/main.css"/>
        </style>
</head>
<body>

</body>
</html>
