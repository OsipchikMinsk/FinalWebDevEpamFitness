package com.epam.osipchik.gym.dao;

import com.epam.osipchik.gym.entity.exercise.Exercise;

public interface ExerciseDao {
    Exercise createExercise(Exercise exercise);
    Exercise getExerciseById(long id);
    boolean updateExercise(Exercise exercise);
    boolean deleteExercise(Exercise exercise);
}
