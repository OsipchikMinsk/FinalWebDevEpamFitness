package com.epam.osipchik.gym.dao.impl;

import com.epam.osipchik.gym.config.DatabaseHandler;
import com.epam.osipchik.gym.dao.AbonementDao;
import com.epam.osipchik.gym.entity.abonement.Abonement;
import java.sql.*;


public class AbonementDaoImpl implements AbonementDao {

    @Override
    public Abonement create(Abonement abonement) {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()){

            PreparedStatement ps = connection.prepareStatement("INSERT INTO abonement VALUES (NULL, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, abonement.getUserId());
            ps.setLong(2, abonement.getTypeId());
            ps.setDate(3, abonement.getStartDate());
            ps.setDate(4, abonement.getFinishDate());
            ps.setDate(5, abonement.getOrderDate());
            ps.setBigDecimal(6, abonement.getTotalPrice());

            if (ps.executeUpdate() == 1) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    abonement.setId(generatedKeys.getLong(1));
                }
                return abonement;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Abonement getAbonementById(long id) {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT*FROM abonement WHERE ID=" + id);
            ResultSet rs = ps.executeQuery("SELECT*FROM abonement WHERE ID=" + id);
            if (rs.next()) {
                return extractAbonementFromResultSet(rs);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

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
    public boolean update(Abonement abonement) {
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
            return ps.executeUpdate() == 1;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Abonement abonement) {
        long id = abonement.getId();
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM abonement WHERE ID=" + id);
            int result = ps.executeUpdate("DELETE FROM abonement WHERE ID=" + id);
            return result == 1;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public int getAbonementsCountByUserId(long id) {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        int result;
        try(Connection connection = databaseHandler.getDbConnection()) {
            //"SELECT* FROM User (SELECT CONT(*) FROM abonement where user.id = abonement.user.id )>5"
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM abonement WHERE USER_ID = ?");
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
