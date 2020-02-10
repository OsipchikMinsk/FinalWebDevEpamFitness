package com.epam.osipchik.gym.controller.util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherHelper {
    private DispatcherHelper() {

    }

    public static void forwardToJsp(HttpServletRequest req, HttpServletResponse resp, String jspTagName) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(jspTagName);
        dispatcher.forward(req, resp);
    }
}
