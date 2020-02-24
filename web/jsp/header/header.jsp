<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="bundle"/>

<c:set var="path" value="${pageContext.request.contextPath}" scope="request"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="s1">
    <div class="sidebar">
        <a class="home" href="${path}/fitrun?command=main_page"> Home</a>
        <a href="${path}/fitrun?command=registration_page#main_form"> Registration</a>
        <a href="${path}/fitrun?command=authorization_page"> Authorization</a>
        <a href="${path}/fitrun?command=show_abonements"> Abonements</a>
    </div>
    <div class="go">
        <p>Just DO IT</p>
    </div>
</div>
</body>
</html>
