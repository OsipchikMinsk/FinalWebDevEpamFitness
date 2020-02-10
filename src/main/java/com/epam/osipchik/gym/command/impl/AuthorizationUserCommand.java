package com.epam.osipchik.gym.command.impl;

import com.epam.osipchik.gym.command.Command;
import com.epam.osipchik.gym.command.CommandProvider;
import com.epam.osipchik.gym.command.CommandType;
import com.epam.osipchik.gym.controller.util.RequestAttributeValue;
import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.entity.user.User;
import com.epam.osipchik.gym.service.UserAuthService;
import com.epam.osipchik.gym.service.impl.ServiceException;
import com.epam.osipchik.gym.service.impl.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AuthorizationUserCommand.class);
    private UserAuthService authService = ServiceFactory.getInstance().getUserService();
    private static final String INVALID_USER_DATA = "Invalid password or login";
    private static final String REGISTRATION_IS_OK = "Authorization is OK!";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, CommandException, ServiceException, DaoException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if ((email == null) || (password == null)) {
            throw new CommandException("Invalid request");
        }
        User user;
        try {
            user = authService.getUserByEmail(email);
            user= authService.authorize(user,password);
        } catch (ServiceException e){
            logger.error(e);
            throw new  CommandException (e);
        }
        Command command;
        if (user == null){
            request.setAttribute(RequestAttributeValue.MESSAGE, INVALID_USER_DATA);
            command = CommandProvider.getInstance().getCommand(CommandType.REGISTRATION.name());
        }else {
            request.setAttribute(RequestAttributeValue.MESSAGE,REGISTRATION_IS_OK);
            command = CommandProvider.getInstance().getCommand(CommandType.MAIN_PAGE.name());
        }
        command.execute(request,response);
    }
}
