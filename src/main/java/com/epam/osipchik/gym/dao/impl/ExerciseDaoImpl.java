package com.epam.osipchik.gym.dao.impl;

import com.epam.osipchik.gym.config.DatabaseHandler;
import com.epam.osipchik.gym.dao.ExerciseDao;
import com.epam.osipchik.gym.entity.exercise.Exercise;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

public class ExerciseDaoImpl implements ExerciseDao {
    @Override
    public Exercise createExercise(Exercise exercise) {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO exercise VALUES (NULL, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, exercise.getName());
            if (ps.executeUpdate() == 1) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                exercise.setId(generatedKeys.getLong(1));
            }
            return exercise;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Exercise getExerciseById(long id) {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT* FROM exercise WHERE ID=" + id);
            ResultSet resultSet = ps.executeQuery("SELECT* FROM exercise WHERE ID=" + id);
            if (resultSet.next()) {
                return extractExerciseFromResultSet(resultSet);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private Exercise extractExerciseFromResultSet(ResultSet rs) throws SQLException {
        Exercise exercise = new Exercise();
        exercise.setId(rs.getInt("ID"));
        exercise.setName(rs.getString("NAME"));
        return exercise;
    }

    @Override
    public boolean updateExercise(Exercise exercise) {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE exercise SET NAME=?, WHERE ID=?");
            ps.setString(1, exercise.getName());
            ps.setLong(2, exercise.getId());
            return ps.executeUpdate() == 1;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteExercise(Exercise exercise) {
        long id = exercise.getId();
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM exercise WHERE ID=" + id);
            int result = ps.executeUpdate("DELETE FROM exercise WHERE ID=" + id);
            return result == 1;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
