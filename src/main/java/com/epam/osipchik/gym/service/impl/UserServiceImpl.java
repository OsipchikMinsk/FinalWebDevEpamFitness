package com.epam.osipchik.gym.service.impl;

import com.epam.osipchik.gym.dao.RoleDao;
import com.epam.osipchik.gym.dao.UserDao;
import com.epam.osipchik.gym.dao.WalletDao;
import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.dao.impl.DaoFactory;
import com.epam.osipchik.gym.entity.role.Roles;
import com.epam.osipchik.gym.entity.user.User;
import com.epam.osipchik.gym.entity.user.Wallet;
import com.epam.osipchik.gym.service.UserService;
import com.epam.osipchik.gym.service.validator.UserDataValidator;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {


    private DaoFactory daoFactory = DaoFactory.getInstance();
    private UserDao userDao = daoFactory.getUserDao();
    private RoleDao roleDao = daoFactory.getRoleDao();
    private WalletDao walletDao = daoFactory.getWalletDao();
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public User authorize(User user, String pass) throws ServiceException {
        String passHash = convertPasswordToHash(pass);
        try {
            if (userDao.getUserPassHash(user.getId()).equals(passHash)) {
                return user;
            }
        } catch (DaoException e) {
            logger.error(e);
            throw  new ServiceException(e);
        }
        return null;
    }

    @Override
    public boolean registerUser(User user, String password, String passwordConfirm) throws ServiceException {
        if (UserDataValidator.isValidDataOfUser(user, password, passwordConfirm)) {
            return register(user, password);
        }
        return false;
    }

    @Override
    public void setupDefaultUserRole(User user) throws DaoException {
        Long roleId = roleDao.getRoleByName(Roles.CLIENT.name());
        roleDao.setUserRole(user.getId(), roleId);
    }

    @Override
    public User getUserByEmail(String email) throws ServiceException {
        User user;
        try {
            user = userDao.getUserByEmail(email);
            return user;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public String getUserRoleByUserId(User user) throws ServiceException {
        long userId = user.getId();
        String userRole;
        long idUserRole;
        try {
            idUserRole = roleDao.getRoleIdByUserId(userId);
            userRole = roleDao.getRoleByRoleId(idUserRole);
            return userRole;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Wallet registerUserWallet(Wallet wallet, User user) throws DaoException, ServiceException {
        wallet.setUserId(user.getId());
        walletDao.createWallet(wallet);
        return wallet;
    }

    @Override
    public Wallet getUserWalletByUserId(User user) throws DaoException, ServiceException {
        return walletDao.getWalletByUserId(user.getId());
    }

    private boolean register(User user, String password) throws ServiceException {
        try {
            userDao.createUser(user);
            userDao.setUserPassHash(user.getId(), convertPasswordToHash(password));
            return true;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }


    //todo pack to private after tests
    @Override
    public String convertPasswordToHash(String password) {
        return DigestUtils.sha256Hex(password);
    }


}
