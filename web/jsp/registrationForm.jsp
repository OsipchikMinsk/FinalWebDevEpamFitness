

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="message" class="java.lang.String" scope="request"/>

<html>
<head>
    <title>Registration Form</title>
    <style>
        <%@include file="/css/main.css"%>

   </style>

</head>
<body>
<form method="post" action="/fitrun" id="reg_form">
    <div class="form-group" id="main_form">
        <input type="hidden" name="command" value="registration"/><br>
        <section class="s1" id="sectionRegistration">
            <input type="text" placeholder="Your name" id="user_name" name="name" required minlength="4"/><br>
            <input type="text" placeholder="Your surname" id="user_surname" name="surname" required minlength="4"/><br>
            <input type="email" placeholder="Your email" id="user_email" name="email"/> <br>
            <input type="password" placeholder="Input password" id="user_password" name="password" required
                   minlength="6"/><br>
            <input type="password" placeholder="Confirm password" id="user_conf_password" name="conf_password"/>
        </section>
        <input type="button" class="btn registr" placeholder="Register" value="Registration" onclick="registrationForm(this)">
        <input type="button" class="btn cancel" placeholder="Cancel" onclick="window.location='../index.jsp'"
               value="Cancel"/>
    </div>
</form>
<p style="font-size: 20px; margin-left: 10%">${message}</p>
<script src="${pageContext.request.contextPath}/js/registration.js"></script>
</body>
</html>
