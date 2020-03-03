package com.epam.osipchik.gym.dao.impl;

import com.epam.osipchik.gym.config.DatabaseHandler;
import com.epam.osipchik.gym.dao.WalletDao;
import com.epam.osipchik.gym.entity.user.User;
import com.epam.osipchik.gym.entity.user.Wallet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WalletDaoImpl implements WalletDao {
    private static final Logger logger = LogManager.getLogger(WalletDaoImpl.class);

    @Override
    public Wallet createWallet(Wallet wallet) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO wallet VALUES (NULL,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setBigDecimal(1, BigDecimal.valueOf(100));//default value of wallet
            ps.setLong(2, wallet.getUserId());
            int result = ps.executeUpdate();
            if (result == 1) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    wallet.setId(generatedKeys.getLong(1));
                    }
                logger.info("Created wallet ");
                return wallet;
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e);
            throw new DaoException(e);
        }
      return null;
    }

    @Override
    public boolean updateWallet(Wallet wallet) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE wallet SET AMOUNT=?,user_id=?, WHERE ID=?");
            ps.setBigDecimal(1, (wallet.getAmount()));
            ps.setLong(2, wallet.getUserId());
            ps.setLong(3, wallet.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }
    @Override
    public Wallet getWalletByUserId(long id) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try(Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM wallet WHERE user_id=?");
          ps.setLong(1,id);
          ResultSet rs = ps.executeQuery();
          if (rs.next()){
              return extractWalletFromResultSet(rs);
          }
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return null;
    }
    private Wallet extractWalletFromResultSet (ResultSet rs) throws SQLException {
        Wallet wallet = new Wallet();
        wallet.setId(rs.getLong("ID"));
        wallet.setAmount(rs.getBigDecimal("AMOUNT"));
        wallet.setUserId(rs.getLong("user_id"));
        return wallet;
    }
}
