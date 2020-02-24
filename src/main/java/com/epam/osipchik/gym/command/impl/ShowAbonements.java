package com.epam.osipchik.gym.command.impl;

import com.epam.osipchik.gym.command.Command;
import com.epam.osipchik.gym.controller.util.DispatcherHelper;
import com.epam.osipchik.gym.controller.util.JspTagName;
import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.service.AbonementService;
import com.epam.osipchik.gym.service.impl.ServiceException;
import com.epam.osipchik.gym.service.impl.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ShowAbonements implements Command {
    private AbonementService abonemnetService = ServiceFactory.getInstance().getAbonemnetService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DaoException, ServiceException, CommandException {

        List<Map<String, Object>> abonementsList = abonemnetService.getAllAbonementsTypeData();
        abonementsList.forEach(data -> {
            System.out.println("----------asdasd");
            System.out.println(data.get("name"));
            System.out.println(data.get("price"));
            Stream.of(data.get("exers")).forEach(ex->{
                System.out.println(ex);
            });
        });
        request.setAttribute("abonements", abonementsList);
        DispatcherHelper.forwardToJsp(request,response, JspTagName.SHOW_ABONEMENTS);



    }
}
