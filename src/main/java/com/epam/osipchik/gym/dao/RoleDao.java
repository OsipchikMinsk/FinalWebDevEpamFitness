package com.epam.osipchik.gym.dao;

import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.entity.role.Roles;

public interface RoleDao {
    Long getRoleByName(String name) throws DaoException;
    void setUserRole(Long userId, Long roleId) throws DaoException;
}
