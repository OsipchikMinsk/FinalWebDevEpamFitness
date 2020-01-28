package com.epam.osipchik.gym.dao;

import com.epam.osipchik.gym.entity.exercise.UserExercise;

public interface UserExerciseDao {
    UserExercise createUserExercise(UserExercise userExercise);
    UserExercise getUserExerciseById(long id);
    boolean updateUserExercise(UserExercise userExercise);
    boolean deleteUserExercise(UserExercise userExercise);
}
