package com.epam.osipchik.gym.dao;

import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.entity.role.Roles;
import com.epam.osipchik.gym.entity.user.User;

public interface RoleDao {
    Long getRoleByName(String name) throws DaoException;
    void setUserRole(Long userId, Long roleId) throws DaoException;
    Long getRoleIdByUserId (long id) throws DaoException;
    String getRoleByRoleId (long id) throws  DaoException;
    String getUserRole (User user) throws DaoException;


}
