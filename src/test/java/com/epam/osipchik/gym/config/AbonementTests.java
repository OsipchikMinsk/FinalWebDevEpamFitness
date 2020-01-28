package com.epam.osipchik.gym.config;

import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.dao.impl.DaoFactory;
import com.epam.osipchik.gym.entity.abonement.Abonement;
import com.epam.osipchik.gym.entity.user.User;
import org.junit.Test;

public class AbonementTests {
    DaoFactory daoFactory = new DaoFactory();
    @Test
    void createAbonement() throws DaoException {

        Abonement abonement = new Abonement();
        User user = daoFactory.getUserDao().getUser(26);


    }

}
