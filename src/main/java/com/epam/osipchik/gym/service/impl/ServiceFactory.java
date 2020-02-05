package com.epam.osipchik.gym.service.impl;

import com.epam.osipchik.gym.service.UserAuthService;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final UserAuthService userService = new UserServiceImpl();


    public ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserAuthService getUserService() {
        return userService;
    }
}
