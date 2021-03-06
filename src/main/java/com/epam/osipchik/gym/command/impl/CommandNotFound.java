package com.epam.osipchik.gym.command.impl;

import com.epam.osipchik.gym.command.Command;
import com.epam.osipchik.gym.controller.util.DispatcherHelper;
import com.epam.osipchik.gym.controller.util.JspTagName;
import com.epam.osipchik.gym.service.impl.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandNotFound implements Command {

    private static final Logger logger = LogManager.getLogger(CommandNotFound.class);



    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)  {

        try {
            DispatcherHelper.forwardToJsp(request,response, JspTagName.ERROR_PAGE);
        }catch (ServletException | IOException e){
            logger.error(e);
        }

    }

}
