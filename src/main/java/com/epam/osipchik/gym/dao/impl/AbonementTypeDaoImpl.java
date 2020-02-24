package com.epam.osipchik.gym.dao.impl;

import com.epam.osipchik.gym.config.DatabaseHandler;
import com.epam.osipchik.gym.dao.AbonementTypeDao;
import com.epam.osipchik.gym.entity.abonement.AbonementType;
import com.epam.osipchik.gym.entity.exercise.Exercise;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AbonementTypeDaoImpl implements AbonementTypeDao {
private static final Logger logger = LogManager.getLogger(AbonementTypeDaoImpl.class);
    @Override
    public AbonementType create(AbonementType abonementType) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {

            PreparedStatement ps = connection.prepareStatement("INSERT INTO abonement_type VALUES (NULL, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, abonementType.getName());
            ps.setInt(2, abonementType.getPrice());

            //todo вынести в метод
            if (ps.executeUpdate() == 1) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    abonementType.setId(generatedKeys.getLong(1));
                }
                logger.info("Created AbonementType" + abonementType);
                return abonementType;
            }

        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return null;
    }

    @Override
    public AbonementType getAbonemenTypetById(long id) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try {
            try (Connection connection = databaseHandler.getDbConnection()) {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM abonement_type WHERE ID=?");
                ps.setLong(1, id);
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    return extractAbonementTypeFromResultSet(resultSet);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return null;
    }
   private AbonementType extractAbonementTypeFromResultSet(ResultSet rs) throws SQLException {
        AbonementType abonementType = new AbonementType();
        abonementType.setId(rs.getInt("ID"));
        abonementType.setName(rs.getString("NAME"));
        abonementType.setPrice(rs.getInt("PRICE"));
        return abonementType;
    }
    @Override
    public boolean update(AbonementType abonementType) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE abonement_type SET NAME=?,PRICE=? WHERE ID=?");
            ps.setString(1, abonementType.getName());
            ps.setInt(2, abonementType.getPrice());
            ps.setLong(3, abonementType.getId());
            logger.info("Update AbonementType" + abonementType);
            return ps.executeUpdate() == 1;
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(AbonementType abonementType) throws DaoException {
        long id = abonementType.getId();
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM abonement_type WHERE ID=?");
            ps.setLong(1, id);
            int result = ps.executeUpdate();
            logger.info("Deleted AbonementType" + abonementType);
            return result == 1;
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public Integer getAbonementPrice(Long abonementTypeId) throws DaoException {
        return null;
    }

    @Override
    public List<AbonementType> getAllAbonementsTypeData() throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM abonement_type");
            ResultSet resultSet = ps.executeQuery();
            List<AbonementType> abonementTypes = new ArrayList<>();
            while (resultSet.next()) {
                AbonementType abonementType = new AbonementType();
                System.out.println("aabon ID: " + resultSet.getLong("ID"));
                abonementType.setId(resultSet.getLong("ID"));
                abonementType.setName(resultSet.getString("NAME"));
                abonementType.setPrice(resultSet.getInt("PRICE"));
                abonementTypes.add(abonementType);
            }
            return abonementTypes;
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }

    }
}
