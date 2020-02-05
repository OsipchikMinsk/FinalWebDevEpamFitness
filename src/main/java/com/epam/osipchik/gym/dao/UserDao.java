package com.epam.osipchik.gym.dao;

import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.entity.user.User;

public interface UserDao {
    User getUser(long id) throws DaoException;

    User getUserByEmail(String email) throws DaoException;

    User createUser(User user) throws DaoException;

    boolean updateUser(User user) throws DaoException;

    boolean deleteUser(User user) throws DaoException;

    void setUserPassHash(long id, String passHash) throws DaoException;

    String getUserPassHash(long id) throws DaoException;
}
