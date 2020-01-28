package com.epam.osipchik.gym.dao.impl;

import com.epam.osipchik.gym.dao.*;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();
    private final UserDao userDao = new UserDaoImpl();
    private final AbonementDao abonementDao = new AbonementDaoImpl();
    private final AbonementTypeDao abonementTypeDao = new AbonementTypeDaoImpl();
    private final ExerciseDao exerciseDao = new ExerciseDaoImpl();
    private final UserExerciseDao userExerciseDao = new UserExerciseDaoImpl();



    public DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return instance;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public AbonementDao getAbonementDao() {
        return abonementDao;
    }

    public AbonementTypeDao getAbonementTypeDao() {
        return abonementTypeDao;
    }

    public ExerciseDao getExerciseDao() {
        return exerciseDao;
    }
    public UserExerciseDao getUserExerciseDao() {
        return userExerciseDao;
    }
}
