package com.epam.osipchik.gym.dao;

import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.entity.exercise.Exercise;

public interface ExerciseDao {
    Exercise createExercise(Exercise exercise) throws DaoException;
    Exercise getExerciseById(long id) throws DaoException;
    boolean updateExercise(Exercise exercise) throws DaoException;
    boolean deleteExercise(Exercise exercise) throws DaoException;
}
