package com.epam.osipchik.gym.dao;

import com.epam.osipchik.gym.entity.abonement.AbonementType;

public interface AbonementTypeDao {
    AbonementType create(AbonementType abonementType);
    AbonementType getAbonemenTypetById (long id);
    boolean update (AbonementType abonementType);
    boolean delete (AbonementType abonementType);
}
