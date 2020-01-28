<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:forEach var="exerciseName" items="${listNames}">
    <c:out value="${exerciseName}"/>
</c:forEach>
привет

</body>
</html>
