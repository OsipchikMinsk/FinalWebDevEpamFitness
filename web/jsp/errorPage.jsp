
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<jsp:useBean id="message" class="java.lang.String" scope="request"/>

<html>
<head>
    <title>Error page</title>
</head>
<body>

<c:if test="${empty message}">
    <p> No such page.</p>
</c:if>
<c:if test="${not empty message}">
    <p>${message}.</p>
</c:if>
<a href="${pageContext.request.contextPath}/fitrun">Main page.</a>

</body>
</html>
