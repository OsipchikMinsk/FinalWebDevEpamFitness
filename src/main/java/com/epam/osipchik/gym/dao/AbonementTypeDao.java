package com.epam.osipchik.gym.dao;

import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.entity.abonement.AbonementType;

public interface AbonementTypeDao {
    AbonementType create(AbonementType abonementType) throws DaoException;
    AbonementType getAbonemenTypetById (long id) throws DaoException;
    boolean update (AbonementType abonementType) throws DaoException;
    boolean delete (AbonementType abonementType) throws DaoException;
}
