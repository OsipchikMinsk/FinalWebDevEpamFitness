
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="message" class="java.lang.String" scope="request"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

<form method="post" action="/fitrun" id="auth_form">
    <div class="authForm" id="main_form">
        <input type="hidden" name="command" value="authorization"/><br>
        <section class="s1" id="sectionRegistration">
            <input type="email" placeholder="Your email" id="user_email" name="email"/> <br>
            <input type="password" placeholder="Input password" id="user_password" name="password" required
                   minlength="6"/><br>
        </section>
        <input type="button" class="btn auth" placeholder="Authorization" value="Authorization"
               onclick="authForm(this)">
        <input type="button" class="btn cancel" placeholder="Cancel" onclick="window.location='../index.jsp'"
               value="Cancel"/>
    </div>
</form>
<c:import url="footer/footer.jsp" charEncoding="utf-8"/>
<p style="font-size: 20px; margin-left: 10%">${message}</p>
<script src="${pageContext.request.contextPath}/js/authorozation.js"></script>
</body>
</html>
