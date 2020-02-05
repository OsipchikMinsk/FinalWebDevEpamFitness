package com.epam.osipchik.gym.service;

import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.entity.comment.Comment;
import com.epam.osipchik.gym.service.impl.ServiceException;

import java.util.List;

public interface CommentService {
    Comment postComment (Comment comment) throws  ServiceException;
    boolean removeComment(Comment comment) throws ServiceException;
    List<Comment> getAllComments() throws ServiceException;
}
