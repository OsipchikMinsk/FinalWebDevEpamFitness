package com.epam.osipchik.gym.dao.impl;

import com.epam.osipchik.gym.config.DatabaseHandler;
import com.epam.osipchik.gym.dao.AbonementTypeDao;
import com.epam.osipchik.gym.entity.abonement.AbonementType;

import java.sql.*;

public class AbonementTypeDaoImpl implements AbonementTypeDao {

    @Override
    public AbonementType create(AbonementType abonementType) {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        try (Connection connection = databaseHandler.getDbConnection()){

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
                return abonementType;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AbonementType getAbonemenTypetById(long id) {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        try {
            try (Connection connection = databaseHandler.getDbConnection()) {
                PreparedStatement ps = connection.prepareStatement("SELECT* FROM abonement_type WHERE ID=" + id);
                ResultSet resultSet = ps.executeQuery("SELECT* FROM abonement_type WHERE ID=" + id);
                if (resultSet.next()) {
                    return extractAbonementTypeFromResultSet(resultSet);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
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
    public boolean update(AbonementType abonementType) {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        try(Connection connection = databaseHandler.getDbConnection()) {
          PreparedStatement ps = connection.prepareStatement(
                  "UPDATE abonement_type SET NAME=?,PRICE=? WHERE ID=?");
          ps.setString(1,abonementType.getName());
          ps.setInt(2,abonementType.getPrice());
          ps.setLong(3,abonementType.getId());
          return ps.executeUpdate()==1;
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean delete(AbonementType abonementType) {
        long id = abonementType.getId();
        DatabaseHandler databaseHandler = new DatabaseHandler();
        try(Connection connection = databaseHandler.getDbConnection()) {
          PreparedStatement ps = connection.prepareStatement(
                  "DELETE FROM abonement_type WHERE ID="+id);
          int result = ps.executeUpdate("DELETE FROM abonement_type WHERE ID="+id);
          return result==1;
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
