package com.epam.osipchik.gym.service;

import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.entity.user.User;
import com.epam.osipchik.gym.entity.user.Wallet;
import com.epam.osipchik.gym.service.impl.ServiceException;

public interface UserService {

    User authorize(User user, String pass) throws DaoException, ServiceException;
    //todo remove afetr tests
    String convertPasswordToHash(String password);
    boolean registerUser(User user, String password, String passwordConfirm) throws ServiceException;
    void setupDefaultUserRole(User user) throws DaoException;
    User getUserByEmail (String email) throws DaoException, ServiceException;
    String getUserRoleByUserId (User user) throws DaoException, ServiceException;
    Wallet registerUserWallet (Wallet wallet, User user) throws DaoException, ServiceException ;
    Wallet getUserWalletByUserId (User user) throws DaoException, ServiceException ;
}
