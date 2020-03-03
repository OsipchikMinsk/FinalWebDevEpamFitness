<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="bundle"/>
<c:set var="path" value="${pageContext.request.contextPath}" scope="request"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Abonement</title>
    <c:import url="head/head.jsp"/>
    <c:import url="header/header.jsp" charEncoding="utf-8"/>

</head>
<body>

<c:forEach items="${abonements}" var="abonement">
    <form action="/fitrun" method="post" id="abonements">
        <input type="hidden" name="command" value="buy_Abonement"/>
        <input type="hidden" name="abonementTypeId" value="${abonement.typeId}"/>
        <table class="">
        <tr>TYPE ID:</tr> <td><c:out value="${abonement.typeId}"/></td>
        <tr>Abonement Name: <c:out value="${abonement.name}"/></tr><br>
        <c:forEach items="${abonement.exers}" var="exer">
            <tr>Execrise:<c:out value="${exer.name}"/></tr>
        </c:forEach>
        <tr>Price: <c:out value="${abonement.price}"/></tr>
        </table>
        <input type="submit" value="Buy Abonement"/>
    </form>
</c:forEach>
<div style="width: 100%; height: 2%;">

</div>
<c:import url="footer/footer.jsp"/>
</body>
</html>
