package com.epam.osipchik.gym.dao.impl;

import com.epam.osipchik.gym.config.DatabaseHandler;
import com.epam.osipchik.gym.dao.UserExerciseDao;
import com.epam.osipchik.gym.entity.exercise.UserExercise;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class UserExerciseDaoImpl implements UserExerciseDao {
    private static final Logger logger = LogManager.getLogger(UserExerciseDaoImpl.class);
    @Override
    public UserExercise createUserExercise(UserExercise userExercise) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO user_exercise VALUES (NULL,?, ?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setDate(1, userExercise.getExecutionDate());
            ps.setBoolean(2, userExercise.isApproved());
            ps.setBoolean(3, userExercise.isDone());
            ps.setLong(4, userExercise.getExerciseId());
            ps.setLong(5, userExercise.getUserId());
            int state = ps.executeUpdate();
            System.out.println("STATE: " + state);
            if (state == 1) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                System.out.println(generatedKeys);
                if (generatedKeys != null && generatedKeys.next()) {
                    userExercise.setId(generatedKeys.getLong(1));
                }
            }
            logger.info("Created " + userExercise);
            System.out.println("created user exersize: " + userExercise.getUserId());
            return userExercise;
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public UserExercise getUserExerciseById(long id) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT* FROM user_exercise WHERE ID=?");
            ps.setLong(1,id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return extractUserExerciseFromResultSet(resultSet);
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
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
        userExercise.setUserId(rs.getLong("USER_ID"));
        return userExercise;
    }

    @Override
    public boolean updateUserExercise(UserExercise userExercise) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE user_exercise SET EXECUTION_DATE=?," +
                                                 "APPROVED=?," +
                                                 "DONE=?," +
                                                 "EXERCISE_ID=?, " +
                                                 "USER_ID=? " +
                            "WHERE ID=?");
            ps.setDate(1, userExercise.getExecutionDate());
            ps.setBoolean(2, userExercise.isApproved());
            ps.setBoolean(3, userExercise.isDone());
            ps.setLong(4, userExercise.getExerciseId());
            ps.setLong(5, userExercise.getUserId());
            ps.setLong(6, userExercise.getId());
            logger.info("Update " + userExercise);
            return ps.executeUpdate() == 1;
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public boolean deleteUserExercise(UserExercise userExercise) throws DaoException {
        long id = userExercise.getId();
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM user_exercise WHERE ID=?");
            ps.setLong(1,id);
            int result = ps.executeUpdate();
            logger.info("Deleted " + userExercise);
            return result == 1;
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }
}
