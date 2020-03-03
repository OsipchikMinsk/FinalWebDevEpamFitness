package com.epam.osipchik.gym.dao;

import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.entity.abonement.Abonement;

import java.util.List;
import java.util.Map;

public interface AbonementDao {
    int getAbonementsCountByUserId(long id) throws DaoException;
    Abonement create(Abonement abonement) throws DaoException;
    Abonement getAbonementById (long id) throws DaoException;
    boolean update (Abonement abonement) throws DaoException;
    boolean delete (Abonement abonement) throws DaoException;
    List<Map<String, Object>> getAllAbonementsByUserId(long userId) throws DaoException;



}
