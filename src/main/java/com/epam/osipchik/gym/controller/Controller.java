package com.epam.osipchik.gym.controller;

import com.epam.osipchik.gym.command.Command;
import com.epam.osipchik.gym.command.CommandProvider;
import com.epam.osipchik.gym.command.CommandType;
import com.epam.osipchik.gym.controller.util.RequestParameterValue;

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

    public Controller() {
        super();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8;charset=cp1251");
        String commandType;
        Command command;
        commandType = request.getParameter(RequestParameterValue.COMMAND_NAME);
        System.out.println(commandType);
        command = commandProvider.getCommand(commandType);

        command.execute(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      if(request.getParameter(RequestParameterValue.COMMAND_NAME)==null){
            request.getRequestDispatcher("/jsp/index.jsp").forward(request,response);
        }else {
            doPost(request,response);
        }


    }
}
