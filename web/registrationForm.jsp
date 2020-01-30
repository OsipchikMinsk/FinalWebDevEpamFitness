
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Form</title>
</head>
<body>
<form method="post" action="fitrun">
    <input type="hidden" name="command" value="registration"/><br>
    <input type="text" placeholder="Your name" name="name"/><br>
    <input type="text" placeholder="Your surname" name="surname"/><br>
    <input type="email" placeholder="Your email" name="email"/> <br>
    <input type="password" placeholder="Input password" name="password"/><br>
    <input type="password" placeholder="Confirm password" name="conf_password"/>
    <input type="submit" placeholder="Register" value="Registration">
</form>

</body>
</html>
