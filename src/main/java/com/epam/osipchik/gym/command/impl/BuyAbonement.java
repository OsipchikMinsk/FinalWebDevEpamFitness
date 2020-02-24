package com.epam.osipchik.gym.command.impl;

import com.epam.osipchik.gym.command.Command;
import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.service.AbonementService;
import com.epam.osipchik.gym.service.impl.ServiceException;
import com.epam.osipchik.gym.service.impl.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class BuyAbonement implements Command {

    private static final Logger logger = LogManager.getLogger(BuyAbonement.class);
    private final AbonementService abonementService = ServiceFactory.getInstance().getAbonemnetService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DaoException, ServiceException, CommandException {
        String abonementTypeId = request.getParameter("abonementTypeId");
        System.out.println("abonementTypeId: " + abonementTypeId);
        Map<String, Object> abonementData = abonementService.buyAbonementByAbonementTypeId(Long.parseLong(abonementTypeId));
        abonementData.forEach((k, v) -> {
            System.out.println(k + " : " + v);
        });




    }
}
