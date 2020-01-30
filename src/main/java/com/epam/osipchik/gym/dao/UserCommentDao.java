package com.epam.osipchik.gym.dao;

import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.entity.comment.Comment;

import java.util.List;

public interface UserCommentDao {
    Comment createComment(Comment comment) throws DaoException;
    List<Comment> getAllComment() throws DaoException;
    boolean updateComment (Comment comment) throws DaoException;
    boolean deleteComment (Comment comment) throws DaoException;
}
