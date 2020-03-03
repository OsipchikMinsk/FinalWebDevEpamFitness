
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="message" class="java.lang.String" scope="request"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="bundle"/>
<c:set var="path" value="${pageContext.request.contextPath}" scope="request"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Authorization</title>
    <%--<style>
        <%@include file="/css/main.css"%>
    </style>--%>
    <c:import url="head/head.jsp" charEncoding="utf-8"/>
    <c:import url="header/header.jsp" charEncoding="utf-8"/>

</head>
<body>
<div id="id01" class="modal">
     <form method="post" action="/fitrun" id="auth_form" class="modal-contetnt">
        <div class="container" id="auth">
            <h1>Sign In</h1>
            <p>Please fill this form to enter in your account.</p>
            <hr>
            <input type="hidden" name="command" value="authorization"/><br>
            <label for="email"><b>Email</b></label>
            <input type="email" placeholder="Enter email" id="user_email" name="email" required/> <br>
            <label for="psw"><b>Password</b></label>
            <input type="password" placeholder="Input password" id="user_password" name="password" required/><br>
            <input type="button" class="btn auth" placeholder="Authorization" value="Authorization"
                   onclick="authForm(this)">
            <input type="button" class="btn cancel" placeholder="Cancel" onclick="window.location='../index.jsp'"
                   value="Cancel"/>
        </div>
    </form>
</div>

<c:import url="footer/footer.jsp" charEncoding="utf-8"/>
<p style="font-size: 20px; margin-left: 10%">${message}</p>
<script src="${pageContext.request.contextPath}/js/authorozation.js"></script>
</body>
</html>
