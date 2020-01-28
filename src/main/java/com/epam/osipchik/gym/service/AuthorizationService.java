package com.epam.osipchik.gym.service;

import com.epam.osipchik.gym.entity.user.User;

public interface AuthorizationService {
//    String getPasswordFromHash(String hash);
//    String convertPasswordToHash(String password);

    User authorize(User user, String pass);
    //todo remove afetr tests
    String convertPasswordToHash(String password);
}
