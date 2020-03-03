<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="bundle"/>
<c:set var="path" value="${pageContext.request.contextPath}" scope="request"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Footer</title>
    <c:import url="/css/footer.css"/>
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
</head>
<footer>
    <div class="footer">
        &copy; <fmt:message key="locale.footer.developer" bundle="${bundle}"/>
        <div clas="setLang">
            <a href="${path}/fitrun?command=english_lang">
                <fmt:message key="locale.lang.en" bundle="${bundle}"/> </a>
            <a href="${path}/fitrun?command=russian_lang">
                <fmt:message key="locale.lang.ru" bundle="${bundle}"/> </a>
        </div>
    </div>
</footer>
</html>
