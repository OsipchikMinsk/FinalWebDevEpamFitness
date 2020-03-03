package com.epam.osipchik.gym.controller.util;

import com.epam.osipchik.gym.dao.RoleDao;
import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.entity.role.Roles;
import com.epam.osipchik.gym.entity.user.User;
import com.epam.osipchik.gym.entity.user.Wallet;
import com.epam.osipchik.gym.service.UserService;
import com.epam.osipchik.gym.service.impl.ServiceException;
import com.epam.osipchik.gym.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionWorker {
      private SessionWorker(){

    }
    public static HttpSession getExistingSession(HttpServletRequest request) {
        //will return current session if current session exists, then it will not create a new session.
        return request.getSession(false);
    }
    public static HttpSession createSession(HttpServletRequest request){

          return request.getSession(true);
    }

    public static void addUserToSession (HttpSession httpSession, User user, String role, Wallet wallet){
        httpSession.setAttribute(SessionAttributeName.ID,user.getId());
        httpSession.setAttribute(SessionAttributeName.USER_NAME, user.getName());
        httpSession.setAttribute(SessionAttributeName.USER_SURNAME,user.getSurname());
        httpSession.setAttribute(SessionAttributeName.LOGIN,user.getEmail());
        httpSession.setAttribute(SessionAttributeName.ROLE, Roles.valueOf(role));// проверить
        httpSession.setAttribute(SessionAttributeName.WALLET,wallet.getAmount());//проверить
    }
    public static void deleteUserFromSession (HttpSession httpSession){
        httpSession.removeAttribute(SessionAttributeName.ID);
        httpSession.removeAttribute(SessionAttributeName.USER_NAME);
        httpSession.removeAttribute(SessionAttributeName.USER_SURNAME);
        httpSession.removeAttribute(SessionAttributeName.LOGIN);
        httpSession.removeAttribute(SessionAttributeName.ROLE);
        httpSession.removeAttribute(SessionAttributeName.WALLET);

    }



}
