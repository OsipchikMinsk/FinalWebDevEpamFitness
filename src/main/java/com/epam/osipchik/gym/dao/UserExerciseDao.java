package com.epam.osipchik.gym.dao;

import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.entity.exercise.UserExercise;

public interface UserExerciseDao {
    UserExercise createUserExercise(UserExercise userExercise) throws DaoException;
    UserExercise getUserExerciseById(long id) throws DaoException;
    boolean updateUserExercise(UserExercise userExercise) throws DaoException;
    boolean deleteUserExercise(UserExercise userExercise) throws DaoException;
}
