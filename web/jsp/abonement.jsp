
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Abonement</title>
    <c:import url="head/head.jsp"/>
    <c:import url="header/header.jsp" charEncoding="utf-8"/>

</head>
<body>

<c:forEach items="${abonements}" var="abonement">
    <form action="/fitrun" method="post">
        <input type="hidden" name="command" value="buy_Abonement"/>
        <input type="hidden" name="abonementTypeId" value="${abonement.typeId}"/>
        <tr>TYPE ID: <c:out value="${abonement.typeId}"/></tr><br>
        <tr>Abonement Name: <c:out value="${abonement.name}"/></tr><br>
        <c:forEach items="${abonement.exers}" var="exer">
            <tr>Execrise:<c:out value="${exer.name}"/></tr>
        </c:forEach>
        <tr>Price: <c:out value="${abonement.price}"/></tr>
        <input type="submit" value="Buy Abonement"/>
    </form>
</c:forEach>
<div style="width: 100%; height: 2%;">

</div>
<c:import url="footer/footer.jsp"/>
</body>
</html>
