package com.epam.osipchik.gym.service.impl;

import com.epam.osipchik.gym.service.AbonementService;
import com.epam.osipchik.gym.service.CommentService;
import com.epam.osipchik.gym.service.UserService;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final UserService userService = new UserServiceImpl();
    private final AbonementService abonemnetService = new AbonementServiceImp();
    private final CommentService commentService = new CommentServiceImpl();


    public ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public AbonementService getAbonemnetService() {
        return abonemnetService;
    }

    public CommentService getCommentService() {
        return commentService;
    }
}
