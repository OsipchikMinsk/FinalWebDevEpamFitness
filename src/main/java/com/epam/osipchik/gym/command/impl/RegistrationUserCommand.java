package com.epam.osipchik.gym.command.impl;
import com.epam.osipchik.gym.command.Command;
import com.epam.osipchik.gym.controller.util.JspTagName;
import com.epam.osipchik.gym.controller.util.RequestAttributeValue;
import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.entity.user.User;
import com.epam.osipchik.gym.entity.user.Wallet;
import com.epam.osipchik.gym.service.impl.ServiceException;
import com.epam.osipchik.gym.service.impl.ServiceFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class RegistrationUserCommand implements Command {
       private ServiceFactory serviceFactory = ServiceFactory.getInstance();
       private static final String REGISTRATION_OK = "Registration is OK";
       private static final String REGISTRATION_IS_FAIL = "Registration is fail";

       @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DaoException, ServiceException {
           String name = request.getParameter("name");
           String surname = request.getParameter("surname");
           String email = request.getParameter("email");
           String password = request.getParameter("password");
           String passwordConfirm = request.getParameter("conf_password");
           User user = new User();
           user.setName(name);
           user.setSurname(surname);
           user.setEmail(email);
           Wallet wallet = new Wallet();
           boolean isRegistered = serviceFactory.getUserService().registerUser(user, password, passwordConfirm);
            if (isRegistered) {
                  serviceFactory.getUserService().setupDefaultUserRole(user);
                  request.setAttribute(RequestAttributeValue.MESSAGE,REGISTRATION_OK);

                  serviceFactory.getUserService().registerUserWallet(wallet,user);
                   System.out.println(user);

                  //todo call setupRole
           } else {
                  request.setAttribute(RequestAttributeValue.MESSAGE,REGISTRATION_IS_FAIL);
           }
           String nameOfUser = name+" "+ surname;
           request.setAttribute("nameOfUser",nameOfUser);
           request.getRequestDispatcher(JspTagName.MAIN_PAGE).forward(request,response);

    }
}
