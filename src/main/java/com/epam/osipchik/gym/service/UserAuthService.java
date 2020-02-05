package com.epam.osipchik.gym.service;

import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.entity.user.User;
import com.epam.osipchik.gym.service.impl.ServiceException;

public interface UserAuthService {
//    String getPasswordFromHash(String hash);
//    String convertPasswordToHash(String password);

    User authorize(User user, String pass) throws DaoException, ServiceException;
    //todo remove afetr tests
    String convertPasswordToHash(String password);
    boolean registerUser(User user, String password, String passwordConfirm) throws ServiceException;
    void setupDefaultUserRole(User user) throws DaoException;
}
