
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Form</title>
    <style>
        <%@include file="/css/main.css"%>

    </style>
</head>
<body>
<form method="post" action="/fitrun">
    <input type="hidden" name="command" value="registration"/><br>
    <input type="text" placeholder="Your name" name="name" required minlength="4"/><br>
    <input type="text" placeholder="Your surname" name="surname" required minlength="4"/><br>
    <input type="email" placeholder="Your email" name="email" /> <br>
    <input type="password" placeholder="Input password" name="password" required minlength="6"/><br>
    <input type="password" placeholder="Confirm password" name="conf_password"/>
    <input type="submit" class="btn registr" placeholder="Register" value="Registration">
    <input type="button" class="btn cancel" placeholder="Cancel" onclick="window.location='../index.jsp'" value="Cancel"/>
</form>

</body>
</html>
