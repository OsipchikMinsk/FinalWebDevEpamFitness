package com.epam.osipchik.gym.config;

import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.dao.impl.DaoFactory;
import com.epam.osipchik.gym.entity.abonement.Abonement;
import com.epam.osipchik.gym.entity.user.User;
import com.epam.osipchik.gym.service.impl.ServiceFactory;
import org.junit.Test;

import java.util.stream.Stream;

public class AbonementTests {
    DaoFactory daoFactory = new DaoFactory();
    ServiceFactory serviceFactory = new ServiceFactory();
    @Test
    public void createAbonement() throws DaoException {

        serviceFactory.getAbonemnetService().getAllAbonementsTypeData().forEach(data -> {
            System.out.println(data.get("name"));
            System.out.println(data.get("price"));
            Stream.of(data.get("exers")).forEach(ex->{
                System.out.println(ex);
            });
        });


    }

}
