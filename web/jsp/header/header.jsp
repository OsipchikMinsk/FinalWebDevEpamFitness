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
    <div class="sidebar">
        <a class="home" href="${path}/fitrun?command=main_page"><fmt:message key="locale.header.home" bundle="${bundle}"/></a>
        <a href="${path}/fitrun?command=registration_page#main_form"><fmt:message key="locale.header.registration" bundle="${bundle}"/></a>
        <a href="${path}/fitrun?command=authorization_page#auth"><fmt:message key="locale.header.authorization" bundle="${bundle}"/></a>

        <c:if test="${sessionScope.role eq 'CLIENT'}">
            <a href="${path}/fitrun?command=show_abonements#abonements"> <fmt:message key="locale.header.abonements"
                                                                         bundle="${bundle}"/></a>
        </c:if>
        <c:if test="${not empty sessionScope.role}">
            <a href="${path}/fitrun?command=log_out"><fmt:message key="locale.header.log_out" bundle="${bundle}"/> </a>
        </c:if>
        <a href="#">Link 3</a>

    </div>
    <div class="s1">
</div>
<div class="go">
    <p>Just DO IT</p>
</div>

</body>
</html>
