package com.epam.osipchik.gym.dao;

import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.entity.user.User;
import com.epam.osipchik.gym.entity.user.Wallet;

public interface WalletDao {

   Wallet createWallet (Wallet wallet) throws DaoException;
   boolean updateWallet (Wallet wallet) throws DaoException;
   Wallet getWalletByUserId (long id) throws DaoException;

}
