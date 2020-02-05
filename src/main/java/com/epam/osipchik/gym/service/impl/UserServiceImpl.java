package com.epam.osipchik.gym.service.impl;

import com.epam.osipchik.gym.dao.RoleDao;
import com.epam.osipchik.gym.dao.UserDao;
import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.dao.impl.DaoFactory;
import com.epam.osipchik.gym.entity.role.Roles;
import com.epam.osipchik.gym.entity.user.User;
import com.epam.osipchik.gym.service.UserAuthService;
import com.epam.osipchik.gym.service.validator.UserDataValidator;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserAuthService {


    private DaoFactory daoFactory = DaoFactory.getInstance();
    private UserDao userDao = daoFactory.getUserDao();
    private RoleDao roleDao = daoFactory.getRoleDao();
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
        return new User();
    }

    @Override
    public boolean registerUser(User user, String password, String passwordConfirm) throws ServiceException {
        System.out.println("start validation");
//        password = convertPasswordToHash(password);
        if (UserDataValidator.isValidDataOfUser(user, password, passwordConfirm)) {
            return register(user, password);
        }
        return false;
    }

    @Override
    public void setupDefaultUserRole(User user) throws DaoException {
        System.out.println("start creating roles");
        Long roleId = roleDao.getRoleByName(Roles.CLIENT.name());
        roleDao.setUserRole(user.getId(), roleId);
    }

    private boolean register(User user, String password) throws ServiceException {
        System.out.println("start registration");
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
