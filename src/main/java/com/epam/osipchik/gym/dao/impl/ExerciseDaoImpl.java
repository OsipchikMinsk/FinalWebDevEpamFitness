package com.epam.osipchik.gym.dao.impl;

import com.epam.osipchik.gym.config.DatabaseHandler;
import com.epam.osipchik.gym.dao.ExerciseDao;
import com.epam.osipchik.gym.entity.comment.Comment;
import com.epam.osipchik.gym.entity.exercise.Exercise;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDaoImpl implements ExerciseDao {
    private static final Logger logger = LogManager.getLogger(ExerciseDaoImpl.class);

    @Override
    public Exercise createExercise(Exercise exercise) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO exercise VALUES (NULL, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, exercise.getName());
            if (ps.executeUpdate() == 1) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                exercise.setId(generatedKeys.getLong(1));
            }
            logger.info("Created Exercise" + exercise);
            return exercise;
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public Exercise getExerciseById(long id) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT* FROM exercise WHERE ID=?");
            ps.setLong(1,id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return extractExerciseFromResultSet(resultSet);
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
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
    public boolean updateExercise(Exercise exercise) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE exercise SET NAME=?, WHERE ID=?");
            ps.setString(1, exercise.getName());
            ps.setLong(2, exercise.getId());
            logger.info("Update Exercise" + exercise);
            return ps.executeUpdate() == 1;
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public boolean deleteExercise(Exercise exercise) throws DaoException {
        long id = exercise.getId();
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM exercise WHERE ID=?");
            ps.setLong(1, id);
            int result = ps.executeUpdate();
            logger.info("Deleted Exercise" + exercise);
            return result == 1;
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<Exercise> getExercisesByAbonementType(Long abonementTypeId) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        List<Exercise> exercises = new ArrayList<>();
        try (Connection connection = databaseHandler.getDbConnection()) {
           // PreparedStatement ps = connection.prepareStatement("SELECT EXERCISE_ID FROM abonement_type_exercise WHERE ABONEMENT_TYPE_ID = ?");
//            PreparedStatement ps = connection.prepareStatement("SELECT * FROM exercise WHERE exercise.ID = " +
//                    "(SELECT EXERCISE_ID FROM abonement_type_exercise WHERE ABONEMENT_TYPE_ID = ?)");
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM exercise JOIN abonement_type_exercise " +
                                                                        "ON exercise.ID = abonement_type_exercise.EXERCISE_ID " +
                                                                   "WHERE ABONEMENT_TYPE_ID = ?");
            ps.setLong(1, abonementTypeId);
            ResultSet resultSet = ps.executeQuery();

                while (resultSet.next()) {
                    Exercise exercise = new Exercise();
                    System.out.println("ad: " + resultSet.getLong("ID"));
                    System.out.println("name: " + resultSet.getString("NAME"));
                    exercise.setId(resultSet.getLong("ID"));
                    exercise.setName(resultSet.getString("NAME"));
                    exercises.add(exercise);
                }

        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return exercises;
    }


}
