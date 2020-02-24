
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:useBean id="message" class="java.lang.String" scope="request"/>

<html>
<head>
    <title>Registration Form</title>
    <c:import url="head/head.jsp"/>
    <c:import url="header/header.jsp" charEncoding="unf-8"/>
   </head>
<body>

<form method="post" action="/fitrun" id="reg_form">
    <div class="form-group" id="main_form">
        <h1>Register</h1>
        <p>Please fill in this form to create an account.</p>
        <hr>
        <input type="hidden" name="command" value="registration"/><br>
        <label for="user_name"><b>Name</b></label>
            <input type="text" placeholder="Your name" id="user_name" name="name" required minlength="4"/>
        <label for="user_surname"><b>Surname</b></label>
            <input type="text" placeholder="Your surname" id="user_surname" name="surname" required minlength="4"/>
        <label for="user_email"><b>Email</b></label>
            <input type="email" placeholder="Your email" id="user_email" name="email"/>
        <label for="user_password"><b>Password</b></label>
            <input type="password" placeholder="Input password" id="user_password" name="password" required
                   minlength="6"/>
        <label for="user_conf_password"><b>Confirm password</b></label>
            <input type="password" placeholder="Confirm password" id="user_conf_password" name="conf_password"/>
            <input type="button" class="btn registr" placeholder="Register" value="Registration"
               onclick="registrationForm(this)">
        <input type="button" class="btn cancel" placeholder="Cancel" onclick="window.location='../index.jsp'"
               value="Cancel"/>
    </div>
</form>
    <div class="signinFromReg">
        <p>Already have an account? <a href="/jsp/authorization.jsp">Sign in</a>.</p>
    </div>
<div class="toUpp"></div>
<c:import url="footer/footer.jsp" charEncoding="utf-8"/>
<script src="${pageContext.request.contextPath}/js/registration.js"></script>
</body>
</html>
