function registrationForm() {
    var massage = "Error with: ";
    var registrationIsOk = true;
    var inputData = 4;
    var passwordLength = 6;
    var reg = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    if (document.getElementById("user_name").value === null || document.getElementById("user_name").value.length < inputData) {
        massage += " Enter correct your name (minimal length of name  4 and more symbols! \n ";
        registrationIsOk = false;
    }
    if (document.getElementById("user_surname").value === null || document.getElementById("user_surname").value.length < inputData) {
        massage += " Enter correct your surname! \n";
        registrationIsOk = false;
    }
    if (!reg.test(document.getElementById("user_email").value.toLowerCase())) {
        massage += " Enter correct email! \n";
        registrationIsOk = false;
    }
    if (document.getElementById("user_password").value === null || document.getElementById("user_password").value.length < passwordLength) {
        massage += " Enter correct your password (minimal length of password 6 and more symbols! \n)"
    }
    if (!document.getElementById("user_password").value === document.getElementById("user_conf_password").value) {
        massage += " Password and confirm password are different! \n";
        registrationIsOk = false;
    }
    if (registrationIsOk) {
        document.getElementById("reg_form").submit();
    } else {
        alert(massage);
    }
}
