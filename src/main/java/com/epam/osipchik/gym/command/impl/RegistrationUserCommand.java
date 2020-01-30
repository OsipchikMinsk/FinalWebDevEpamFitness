package com.epam.osipchik.gym.command.impl;

import com.epam.osipchik.gym.command.Command;
import com.epam.osipchik.gym.dao.impl.DaoFactory;
import com.epam.osipchik.gym.entity.user.User;
import com.epam.osipchik.gym.service.impl.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class RegistrationUserCommand implements Command {
       @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
           response.setContentType("text/html;charset=UTF-8;charset=cp1251");
           String name = request.getParameter("name");
           String surname = request.getParameter("surname");
           String email = request.getParameter("email");
           String password = request.getParameter("password");
           DaoFactory daoFactory = DaoFactory.getInstance();
           ServiceFactory serviceFactory = ServiceFactory.getInstance();
           String passHash =
                   serviceFactory.getAuthorizationService().convertPasswordToHash(password);

           User user = new User();
           user.setName(name);
           user.setSurname(surname);
           user.setEmail(email);
// todo Services login
           daoFactory.getUserDao().createUser(user);
           long id = user.getId();
           daoFactory.getUserDao().setUserPassHash(id,passHash);
// todo Add User_Role (find role for user and "INSERT INTO user_role values (user_id, role_id)"
           request.setAttribute("userSurename",surname);
           request.getRequestDispatcher("/index.jsp").forward(request,response);
//           response.sendRedirect("index.jsp");

           /*PrintWriter out = response.getWriter();
           out.println("<h1>Hello, " + user.getName() + user.getSurname() + "/h1>");
*/


    }
}
