package com.epam.osipchik.gym.service;

import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.entity.comment.Comment;

import java.util.List;

public interface CommentService {
    Comment postComment (Comment comment) throws DaoException;
    void removeComment(Comment comment) throws DaoException;
    List<Comment> getAllComments() throws DaoException;
}
