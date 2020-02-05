package com.epam.osipchik.gym.dao.impl;

import com.epam.osipchik.gym.config.DatabaseHandler;
import com.epam.osipchik.gym.dao.RoleDao;
import com.epam.osipchik.gym.entity.role.Roles;
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
        System.out.println("setting role");
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
