package com.epam.osipchik.gym.dao.impl;

import com.epam.osipchik.gym.config.DatabaseHandler;
import com.epam.osipchik.gym.dao.UserExerciseDao;
import com.epam.osipchik.gym.entity.exercise.UserExercise;

import java.sql.*;

public class UserExerciseDaoImpl implements UserExerciseDao {
    @Override
    public UserExercise createUserExercise(UserExercise userExercise) {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO user_exercise VALUES (NULL,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, userExercise.getExecutionDate());
            ps.setBoolean(2, userExercise.isApproved());
            ps.setBoolean(3, userExercise.isDone());
            ps.setLong(4, userExercise.getExerciseId());
            if (ps.executeUpdate() == 1) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                userExercise.setId(generatedKeys.getLong(1));
            }
            return userExercise;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserExercise getUserExerciseById(long id) {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT* FROM user_exercise WHERE ID=" + id);
            ResultSet resultSet = ps.executeQuery("SELECT* FROM user_exercise WHERE ID=" + id);
            if (resultSet.next()) {
                return extractUserExerciseFromResultSet(resultSet);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private UserExercise extractUserExerciseFromResultSet(ResultSet rs) throws SQLException {
        UserExercise userExercise = new UserExercise();
        userExercise.setId(rs.getInt("ID"));
        userExercise.setExecutionDate(rs.getDate("EXECUTION_DATE"));
        userExercise.setApproved(rs.getBoolean("APPROVED"));
        userExercise.setDone(rs.getBoolean("DONE"));
        userExercise.setExerciseId(rs.getLong("EXERCISE_ID"));
        return userExercise;
    }

    @Override
    public boolean updateUserExercise(UserExercise userExercise) {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE user_exercise SET EXECUTION_DATE=?,APPROVED=?,DONE=?," +
                            "EXERCISE_ID=?,WHERE ID=?");
            ps.setDate(1, userExercise.getExecutionDate());
            ps.setBoolean(2, userExercise.isApproved());
            ps.setBoolean(3, userExercise.isDone());
            ps.setLong(4, userExercise.getExerciseId());
            ps.setLong(5, userExercise.getId());
            return ps.executeUpdate() == 1;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUserExercise(UserExercise userExercise) {
        long id = userExercise.getId();
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM user_exercise WHERE ID=" + id);
            int result = ps.executeUpdate("DELETE FROM user_exercise WHERE ID=" + id);
            return result == 1;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
