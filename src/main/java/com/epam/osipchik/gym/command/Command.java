package com.epam.osipchik.gym.command;

import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.service.impl.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, DaoException, ServiceException;
}
