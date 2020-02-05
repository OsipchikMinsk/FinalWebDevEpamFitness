package com.epam.osipchik.gym.dao.impl;
import com.epam.osipchik.gym.config.DatabaseHandler;
import com.epam.osipchik.gym.dao.UserDao;
import com.epam.osipchik.gym.entity.user.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class UserDaoImpl implements UserDao{
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public User getUser(long id) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT* FROM user WHERE ID=?");
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return extractUserFromResultSet(resultSet);
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);

        }
        return null;
    }
    // todo внутренний клас
    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("ID"));
        user.setName(rs.getString("NAME"));
        user.setSurname(rs.getString("SURNAME"));
        user.setEmail(rs.getString("EMAIL"));
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT *FROM user WHERE EMAIL=?");
            ps.setString(1,email);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                logger.info("Created userByEmail " +  email);
                return extractUserFromResultSet(resultSet);
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return null;
    }

    @Override
    public User createUser(User user) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO user VALUES (NULL, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            if (ps.executeUpdate() == 1) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                }
                logger.info("Created " + user);
                return user;
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return null;
    }

    @Override
    public boolean updateUser(User user) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE user SET NAME=?, SURNAME=?, EMAIL=?,WHERE ID=?");
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setLong(4, user.getId());
            logger.info("Update " + user);
            return ps.executeUpdate() == 1;
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public boolean deleteUser(User user) throws DaoException {
        long id = user.getId();
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM user WHERE ID=?");
            ps.setLong(1,id);
            int result = ps.executeUpdate();
            logger.info("Deleted " + user);
            return result == 1;
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public void setUserPassHash(long id, String passHash) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT user_id FROM user_password WHERE user_id =?");
            ps.setLong(1, id);
            if (ps.execute()) {
                ResultSet resultSet = ps.getResultSet();
                if (resultSet.next()) {
                    ps = connection.prepareStatement("UPDATE user_password SET password =? WHERE user_id =?");
                    ps.setString(1, passHash);
                    ps.setLong(2, id);
                    ps.executeUpdate();
                    logger.info("Update user PassHash" );
                }   else {
                        ps = connection.prepareStatement("INSERT INTO user_password VALUES (? , ?)");
                        ps.setLong(1, id);
                        ps.setString(2, passHash);
                        ps.execute();
                        logger.info("Created user passsword hash");
                    }
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public String getUserPassHash(long id) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        String result = "";
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT password FROM user_password WHERE user_id =?");
            ps.setLong(1, id);
            if (ps.execute()) {
                ResultSet resultSet = ps.getResultSet();
                if (resultSet.next()) {
                    result = resultSet.getString(1);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return result;
    }

}
