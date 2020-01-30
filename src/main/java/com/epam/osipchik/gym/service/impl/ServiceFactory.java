package com.epam.osipchik.gym.service.impl;

import com.epam.osipchik.gym.service.AuthorizationService;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final AuthorizationService authorizationService = new AuthorizationServiceImpl();

    public ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public AuthorizationService getAuthorizationService() {
        return authorizationService;
    }
}
