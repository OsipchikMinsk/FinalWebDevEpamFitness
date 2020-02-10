package com.epam.osipchik.gym.controller;

import com.epam.osipchik.gym.command.Command;
import com.epam.osipchik.gym.command.CommandProvider;
import com.epam.osipchik.gym.command.CommandType;
import com.epam.osipchik.gym.command.impl.CommandException;
import com.epam.osipchik.gym.command.impl.CommandNotFound;
import com.epam.osipchik.gym.controller.util.DispatcherHelper;
import com.epam.osipchik.gym.controller.util.JspTagName;
import com.epam.osipchik.gym.controller.util.RequestAttributeValue;
import com.epam.osipchik.gym.controller.util.RequestParameterValue;
import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.service.impl.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Controller")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final CommandProvider commandProvider = CommandProvider.getInstance();
    private static final Logger logger = LogManager.getLogger(Controller.class);

    public Controller() {
        super();
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            process(request, response);
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            process(request, response);
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    private void process (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        CommandProvider commandProvider = CommandProvider.getInstance();
        String commandType =request.getParameter(RequestParameterValue.COMMAND_NAME);
        if (commandType ==null){
            commandType = CommandType.MAIN_PAGE.name();
        }
        Command command = commandProvider.getCommand(commandType);
        if (command ==null){
            command = new CommandNotFound();
        }
        try {
            command.execute(request, response);
        } catch (DaoException | ServiceException e) {
            logger.error(e);
            request.setAttribute(RequestAttributeValue.MESSAGE, e.getLocalizedMessage());
            DispatcherHelper.forwardToJsp(request,response, JspTagName.ERROR_PAGE);
        }
    }
}
