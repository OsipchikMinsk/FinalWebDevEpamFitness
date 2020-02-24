
<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="utf-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>

    <title>Main Page</title>

    <c:import url="jsp/head/head.jsp" charEncoding="utf-8"/>
    <c:import url="jsp/header/header.jsp" charEncoding="utf-8"/>


</head>
<body>


<div class="user">
    <p>${userSurename}</p>
</div>
<div class="message">
    <c:if test="${not empty message}">
        <p>${message}.</p>
    </c:if>
</div>


<%--<div class="header">
    <input type="button" class="btn registr" onclick="window.location='jsp/registrationForm.jsp'" value="Registration"/>
    <input type="button" class="btn signIn" onclick="window.location='jsp/authorization.jsp'" value="Sign in"/>
    <form method="get" action="/fitrun">
        <input type="hidden" name="command" value="show_Abonements"/><br>
        <input type="submit" value="Show Abonements">
    </form>
</div>--%>
<%--<div class="s1">
    <div class="sidebar">
        <a class="home" href="${pageContext.request.contextPath}/fitrun?command=main_page"> Home</a>
        <a href="${pageContext.request.contextPath}/fitrun?command=REGISTRATION_PAGE"> Registration</a>
        <a href="#autorization"> Authotization</a>
        <a href="#abonements"> Abonements</a>
    </div>
    <div class="go">
        <p>Just DO IT</p>
    </div>
</div>--%>
<c:import url="jsp/footer/footer.jsp" charEncoding="utf-8"/>



</body>
</html>
