
<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="utf-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>

    <title>Exerscise</title>

    <style>
        <%@include file="css/main.css"%>

    </style>

</head>
<body>


   <div class="user">
       <p>${userSurename}</p>
   </div>


<div class="heder">
   <input type="button" class="btn registr" onclick="window.location='jsp/registrationForm.jsp'" value="Registration"/>
   <input type="button" class="btn signIn" onclick="window.location='authorization.jsp'" value="Sign in"/>
</div>
</body>
</html>
