package com.epam.osipchik.gym.command.impl;

import com.epam.osipchik.gym.command.Command;
import com.epam.osipchik.gym.dao.impl.DaoFactory;
import com.epam.osipchik.gym.entity.user.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegistrationUserCommand implements Command {
       @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
           response.setContentType("text/html;charset=UTF-8");

           String name = request.getParameter("name");
           String surname = request.getParameter("surname");
           String email = request.getParameter("email");
           String password = request.getParameter("password");
           DaoFactory daoFactory = DaoFactory.getInstance();
           User user = new User();
           user.setName(name);
           user.setSurname(surname);
           user.setEmail(email);

           daoFactory.getUserDao().createUser(user);
           daoFactory.getUserDao().setUserPassHash(user.getId(),password);
           RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
           requestDispatcher.forward(request,response);
           PrintWriter out = response.getWriter();
           out.println("<h1>Hello, " + user.getName() + user.getSurname() + "/h1>");



    }
}
