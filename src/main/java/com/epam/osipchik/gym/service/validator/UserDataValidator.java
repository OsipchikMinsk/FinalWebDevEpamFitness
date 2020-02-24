package com.epam.osipchik.gym.service.validator;

import com.epam.osipchik.gym.dao.impl.DaoFactory;
import com.epam.osipchik.gym.entity.user.User;
import com.epam.osipchik.gym.service.impl.ServiceFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDataValidator {


    private final static String EMAIL_PATTERN=
            "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private final static int MIN_PASSWORD_LENGTH=6;
    private final static int MIN_NAME_LENGTH=4;
    private final static int MIN_SURNAME_LENGTH=4;

    private UserDataValidator(){

    }
    public static boolean isValidEmail (String email){
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public  static  boolean isValidPassword(String password){
        return (password!=null)&&(!password.isEmpty())&&(password.length()>=MIN_PASSWORD_LENGTH);
    }
    public static boolean isValidName(String name){
        return (name!=null)&&(!name.isEmpty())&&(name.length()>=MIN_NAME_LENGTH);
    }
    public static boolean isValidSurname (String surname){
        return isValidName(surname);
    }
    public static boolean isValidPasswordConfirm (String password, String passwordConfirm){

        return isValidPassword(password)&&isValidPassword(passwordConfirm)&&password.equals(passwordConfirm);
    }
    public static boolean isValidDataOfUser (User user, String password, String passwordConfirm){
        boolean isValidName = isValidName(user.getName());
        System.out.println("name" + isValidName);
        boolean isValidSurname = isValidSurname(user.getSurname());
        System.out.println("surname" + isValidSurname);
        boolean isValidEmail = isValidEmail(user.getEmail());
        System.out.println("email" + isValidEmail);
        boolean isValidPassConfirm = isValidPasswordConfirm(password, passwordConfirm);
        System.out.println("name" + isValidPassConfirm);
        return (isValidName)&&(isValidSurname)&&(isValidEmail)&&(isValidPassConfirm);
    }




}
