package com.epam.osipchik.gym.dao.impl;

import com.epam.osipchik.gym.config.DatabaseHandler;
import com.epam.osipchik.gym.dao.AbonementDao;
import com.epam.osipchik.gym.entity.abonement.Abonement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AbonementDaoImpl implements AbonementDao {

    private static final Logger logger = LogManager.getLogger(AbonementDaoImpl.class);

    @Override
    public Abonement create(Abonement abonement) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()){
            PreparedStatement ps = connection.prepareStatement("INSERT INTO abonement VALUES (NULL, ?, ?, ?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setLong(1, abonement.getUserId());
            ps.setLong(2, abonement.getTypeId());
            ps.setDate(3, abonement.getStartDate());
            ps.setDate(4, abonement.getFinishDate());
            ps.setDate(5, abonement.getOrderDate());
            ps.setBigDecimal(6, abonement.getTotalPrice());
            int result = ps.executeUpdate();
            if (result == 1) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    abonement.setId(generatedKeys.getLong(1));
                }
                logger.info("Created Abonement" + abonement);
                return abonement;
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return null;
    }
    @Override
    public Abonement getAbonementById(long id) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT*FROM abonement WHERE ID=?");
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extractAbonementFromResultSet(rs);
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);

        }
        return null;
    }
    private Abonement extractAbonementFromResultSet(ResultSet rs) throws SQLException {
        Abonement abonement = new Abonement();
        abonement.setId(rs.getLong("ID"));
        abonement.setUserId(rs.getLong("USER_ID"));
        abonement.setStartDate(rs.getDate("START_DATE"));//проверить дату
        abonement.setFinishDate(rs.getDate("FINISH_DATE"));
        abonement.setOrderDate(rs.getDate("ORDER_DATE"));
        abonement.setTotalPrice(rs.getBigDecimal("TOTAL_PRICE"));
        return abonement;
    }

    @Override
    public boolean update(Abonement abonement) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE abonement SET USER_ID=?,TYPE_ID=?,START_DATE=?,FINISH_DATE=?," + //проверить запрос
                            "ORDER_DATE=?,TOTAL_PRICE=?,WHERE ID=?");
            ps.setLong(1, abonement.getUserId());
            ps.setLong(2, abonement.getTypeId());
            ps.setDate(3, abonement.getStartDate());
            ps.setDate(4, abonement.getFinishDate());
            ps.setDate(5, abonement.getOrderDate());
            ps.setBigDecimal(6, abonement.getTotalPrice());
            ps.setLong(7, abonement.getId());
            logger.info("Update Abonement" + abonement);
            return ps.executeUpdate() == 1;
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }

    }

    @Override
    public boolean delete(Abonement abonement) throws DaoException {
        long id = abonement.getId();
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM abonement WHERE ID=?");
            ps.setLong(1,id);
            int result = ps.executeUpdate();
            logger.info("Deletetd " + abonement);
            return result == 1;
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw  new DaoException(e);
        }
    }

    @Override
    public List<Map<String, Object>> getAllAbonementsByUserId(long userId) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT abonement.ID AS ABON_ID, abonement.USER_ID AS ABON_USER_ID, abonement.TYPE_ID AS ABON_TYPE_ID, " +
                            "abonement.START_DATE AS START_DATE, abonement.FINISH_DATE AS FINISH_DATE," +
                    "abonement.TOTAL_PRICE AS TOTAL_PRICE FROM abonement JOIN abonement_type ON abonement.TYPE_ID = abonement_type.ID  WHERE abonement.USER_ID = ?");
            ps.setLong(1, userId);
            ResultSet resultSet = ps.executeQuery();
            List<Map<String, Object>> abonements = new ArrayList<>();
            while (resultSet.next()){
                Map<String, Object> abonement = new HashMap<>();
                abonement.put("ABON_ID", resultSet.getLong(1));
                abonement.put("ABON_USER_ID", resultSet.getLong(2));
                abonement.put("ABON_TYPE_ID", resultSet.getLong(3));
                abonement.put("START_DATE", resultSet.getDate(4));
                abonement.put("FINISH_DATE", resultSet.getDate(5));
                abonement.put("TOTAL_PRICE", resultSet.getLong(6));
                abonements.add(abonement);
                return abonements;
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);

        }return null;
    }

    @Override
    public int getAbonementsCountByUserId(long id) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM abonement WHERE USER_ID = ?");
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return 0;
    }
}
