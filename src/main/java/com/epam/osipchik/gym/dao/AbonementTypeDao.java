package com.epam.osipchik.gym.dao;

import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.entity.abonement.AbonementType;

import java.util.List;
import java.util.Map;

public interface AbonementTypeDao {
    AbonementType create(AbonementType abonementType) throws DaoException;
    AbonementType getAbonemenTypetById (long id) throws DaoException;
    boolean update (AbonementType abonementType) throws DaoException;
    boolean delete (AbonementType abonementType) throws DaoException;
    Integer getAbonementPrice(Long abonementTypeId) throws DaoException;
    List<AbonementType> getAllAbonementsTypeData() throws DaoException;
}
