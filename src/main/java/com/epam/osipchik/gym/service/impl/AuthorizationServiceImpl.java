package com.epam.osipchik.gym.service.impl;

import com.epam.osipchik.gym.dao.UserDao;
import com.epam.osipchik.gym.dao.impl.UserDaoImpl;
import com.epam.osipchik.gym.entity.user.User;
import com.epam.osipchik.gym.service.AuthorizationService;
import org.apache.commons.codec.digest.DigestUtils;

public class AuthorizationServiceImpl  implements AuthorizationService{

    private UserDao userDao = new UserDaoImpl();

    @Override
    public User authorize(User user, String pass) {
        String passHash = convertPasswordToHash(pass);
        if (userDao.getUserPassHash(user.getId()).equals(passHash)) {
            return user;
        }
        return new User();
    }

    //todo pack to private after tests
    public String convertPasswordToHash(String password) {
        return DigestUtils.sha256Hex(password);
    }
}
