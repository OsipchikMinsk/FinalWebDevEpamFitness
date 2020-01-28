package com.epam.osipchik.gym.dao;

import com.epam.osipchik.gym.entity.abonement.Abonement;

import java.util.List;

public interface AbonementDao {
    int getAbonementsCountByUserId(long id);
    Abonement create(Abonement abonement);
    Abonement getAbonementById (long id);
    boolean update (Abonement abonement);
    boolean delete (Abonement abonement);

}
