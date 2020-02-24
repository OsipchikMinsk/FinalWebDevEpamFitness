package com.epam.osipchik.gym.service;

import com.epam.osipchik.gym.dao.impl.DaoException;

import java.util.List;
import java.util.Map;

public interface AbonementService {
    Map<String, Object> getAbonementData(Long abonementTypeId) throws DaoException;
    List<Map<String, Object>> getAllAbonementsTypeData() throws DaoException;
    Map<String, Object> buyAbonementByAbonementTypeId(Long abonementTypeId) throws DaoException;
}
