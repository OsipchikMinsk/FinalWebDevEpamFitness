package com.epam.osipchik.gym.dao.impl;

import com.epam.osipchik.gym.config.DatabaseHandler;
import com.epam.osipchik.gym.dao.RoleDao;
import com.epam.osipchik.gym.entity.user.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDaoImpl implements RoleDao {
    private static final Logger logger = LogManager.getLogger(RoleDaoImpl.class);

    @Override
    public void setUserRole(Long userId, Long roleId) throws DaoException {
       DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO user_role VALUES (?, ?)");
            ps.setLong(1, userId);
            ps.setLong(2, roleId);
            ps.execute();
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public Long getRoleIdByUserId(long id) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try(Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT ROLE_ID FROM user_role WHERE USER_ID=?");
            ps.setLong(1,id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                return resultSet.getLong("ROLE_ID");
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getRoleByRoleId(long id) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT NAME FROM role WHERE ID=?");
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("NAME");
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getUserRole(User user) throws DaoException {
        long userID = user.getId();
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT role.NAME, role.ID, user_role.USER_ID FROM role " +
                    "JOIN user_role ON role.ID = user_role.ROLE_ID WHERE USER_ID=?");
            ps.setLong(1, userID);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("NAME");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Long getRoleByName(String name) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT ID FROM role WHERE NAME=?");
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("ID");
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return null;
    }


}
