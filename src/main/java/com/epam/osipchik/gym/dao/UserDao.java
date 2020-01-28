package com.epam.osipchik.gym.dao;

import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.entity.user.User;

public interface UserDao {
     User getUser(long id) throws DaoException;
    User getUserByEmail(String email);
     User createUser(User user);
     boolean updateUser(User user);
     boolean deleteUser(User user);
    void setUserPassHash(long id, String passHash);
    String getUserPassHash(long id);
}
