package com.epam.osipchik.gym.dao.impl;
import com.epam.osipchik.gym.config.DatabaseHandler;
import com.epam.osipchik.gym.dao.UserDao;
import com.epam.osipchik.gym.entity.user.User;
import java.sql.*;

public class UserDaoImpl implements UserDao{

    @Override
    public User getUser(long id)  {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        try {
            try(Connection connection = databaseHandler.getDbConnection()) {
                PreparedStatement ps = connection.prepareStatement("SELECT* FROM user WHERE ID=" + id);
                ResultSet resultSet = ps.executeQuery("SELECT* FROM user WHERE ID=" + id);
                if (resultSet.next()) {
                    return extractUserFromResultSet(resultSet);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("ID"));
        user.setName(rs.getString("NAME"));
        user.setSurname(rs.getString("SURNAME"));
        user.setEmail(rs.getString("EMAIL"));
        return user;
    }
    @Override
    public User getUserByEmail(String email) {
    //todo   для авторизации может быть
        return null;
    }

    @Override
    public User createUser(User user) {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        try (Connection connection = databaseHandler.getDbConnection()){
            PreparedStatement ps = connection.prepareStatement("INSERT INTO user VALUES (NULL, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());

            if (ps.executeUpdate() == 1) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                }
                return user;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE user SET NAME=?, SURNAME=?, EMAIL=?,WHERE ID=?");
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setLong(4, user.getId());
            return ps.executeUpdate() == 1;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        long id = user.getId();
        DatabaseHandler databaseHandler = new DatabaseHandler();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM user WHERE ID=" + id);
            int result = ps.executeUpdate("DELETE FROM user WHERE ID=" + id);
            return result == 1;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void setUserPassHash(long id, String passHash) {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT user_id FROM user_password WHERE user_id =?");
            ps.setLong(1, id);
            if (ps.execute()) {
                ResultSet resultSet = ps.getResultSet();
                if (resultSet.next()) {
                    ps = connection.prepareStatement("UPDATE user_password SET password =? WHERE user_id =?");
                    ps.setString(1, passHash);
                    ps.setLong(2, id);
                    ps.executeUpdate();
                }   else {
                        ps = connection.prepareStatement("INSERT INTO user_password VALUES (? , ?)");
                        ps.setLong(1, id);
                        ps.setString(2, passHash);
                        ps.execute();
                    }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getUserPassHash(long id) {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        String result = "";
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT password FROM user_password WHERE user_id =?");
            ps.setLong(1, id);
            if (ps.execute()) {
                ResultSet resultSet = ps.getResultSet();
                if (resultSet.next()) {
                    System.out.println("pass: " + resultSet.getString(1));
                    result = resultSet.getString(1);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
