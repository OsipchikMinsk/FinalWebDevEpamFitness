function authForm () {
    var massage = "Error with: ";
    var authIsOk = true;
    var passwordLength = 6;
    var reg = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    if (!reg.test(document.getElementById("user_email").value.toLowerCase())) {
        massage += " Enter correct email! \n";
        authIsOk = false;
    }
    if (document.getElementById("user_password").value === null || document.getElementById("user_password").value.length < passwordLength) {
        massage += " Enter correct your password (minimal length of password 6 and more symbols! \n)"
    }
     if (authIsOk) {
        document.getElementById("auth_form").submit();
    } else {
        alert(massage);
    }

}