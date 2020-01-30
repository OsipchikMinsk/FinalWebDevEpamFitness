package com.epam.osipchik.gym.dao.impl;

import com.epam.osipchik.gym.config.DatabaseHandler;
import com.epam.osipchik.gym.dao.UserCommentDao;
import com.epam.osipchik.gym.entity.comment.Comment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserCommentDaoImpl implements UserCommentDao {

    private static final Logger logger = LogManager.getLogger(UserCommentDaoImpl.class);

    @Override
    public Comment createComment(Comment comment) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO comment VALUES (NULL,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, comment.getUserId());
            ps.setString(2, comment.getDescription());
            if (ps.executeUpdate() == 1) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                comment.setId(generatedKeys.getLong(1));
            } else {
                throw new DaoException("Prepared statement not correct in createComment method");
            }
            return comment;
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<Comment> getAllComment() throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM comment");
            ResultSet resultSet = ps.executeQuery("SELECT * FROM comment");
            List<Comment> comments = new ArrayList<>();
            if (resultSet.next()) {
                while (resultSet.next()) {
                    comments.add(extractCommentFromResultSet(resultSet));
                }
                return comments;
            }
            throw new DaoException("Prepared statement not correct in getCommentByID method");
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    private Comment extractCommentFromResultSet (ResultSet rs) throws SQLException {
        Comment comment = new Comment();
        comment.setId(rs.getLong("ID"));
        comment.setUserId(rs.getLong("USER_ID"));
        comment.setDescription(rs.getString("DESCRIPTION"));
        return comment;
    }


    public Comment getCommentById(long id) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM comment WHERE ID=" + id);
            ResultSet resultSet = ps.executeQuery("SELECT * FROM comment WHERE ID=" + id);
            if (resultSet.next()) {
                return extractCommentFromResultSet(resultSet);
            } else {
                throw new DaoException("Prepared statement not correct in getCommentByID method");
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updateComment(Comment comment) throws DaoException {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE comment SET USER_ID=?, DESCRIPTION=?," +"WHERE ID=?");//todo check USER_ID
            ps.setLong(1, comment.getUserId());
            ps.setString(2, comment.getDescription());
            ps.setLong(3, comment.getId());
            if (ps.executeUpdate() == 1) {
                return true;
            } else {
                throw new DaoException("Prepared statement not correct in updateComment method");
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        //return false;???
    }
    @Override
    public boolean deleteComment(Comment comment) throws DaoException {
        long id = comment.getId();
        int result;
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        try (Connection connection = databaseHandler.getDbConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM comment WHERE ID=" + id);
            result = ps.executeUpdate();
            if (result == 1) {
                return true;
            } else {
                throw new DaoException("Prepared statement not correct in deleteComment method");
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }
}
