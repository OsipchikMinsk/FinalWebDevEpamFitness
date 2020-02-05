package com.epam.osipchik.gym.command.impl;

import com.epam.osipchik.gym.command.Command;
import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.dao.impl.DaoFactory;
import com.epam.osipchik.gym.entity.user.User;
import com.epam.osipchik.gym.service.impl.ServiceException;
import com.epam.osipchik.gym.service.impl.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class RegistrationUserCommand implements Command {
       ServiceFactory serviceFactory = ServiceFactory.getInstance();
       @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DaoException, ServiceException {
              System.out.println("start execute");
           response.setContentType("text/html;charset=UTF-8");
           // паттерн DTO
           String name = request.getParameter("name");
           String surname = request.getParameter("surname");
           String email = request.getParameter("email");
           String password = request.getParameter("password");
           String passwordConfirm = request.getParameter("conf_password");

           /*String passHash =
                   serviceFactory.getUserService().convertPasswordToHash(password);*/
           User user = new User();
           user.setName(name);
           user.setSurname(surname);
           user.setEmail(email);
// todo Services login и убрать dao только сервисы тут

           //daoFactory.getUserDao().createUser(user);

         /*  long id = user.getId();
           daoFactory.getUserDao().setUserPassHash(id,passHash);*/
           boolean isRegistered = serviceFactory.getUserService().registerUser(user, password, passwordConfirm);
           if (isRegistered) {
                  serviceFactory.getUserService().setupDefaultUserRole(user);
                  //todo call setupRole

           }
// todo Add User_Role (find role for user and "INSERT INTO user_role values (user_id, role_id)"
           request.setAttribute("userSurename",surname);
           request.getRequestDispatcher("/index.jsp").forward(request,response);



    }
}
