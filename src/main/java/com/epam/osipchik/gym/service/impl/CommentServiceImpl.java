package com.epam.osipchik.gym.service.impl;

import com.epam.osipchik.gym.dao.UserCommentDao;
import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.dao.impl.DaoFactory;
import com.epam.osipchik.gym.entity.comment.Comment;
import com.epam.osipchik.gym.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    private UserCommentDao userCommentDao = DaoFactory.getInstance().getUserCommentDao();

    @Override
    public Comment postComment(Comment comment) throws DaoException {
        return userCommentDao.createComment(comment);
    }

    @Override
    public void removeComment(Comment comment) throws DaoException {
        userCommentDao.deleteComment(comment);
    }

    @Override
    public List<Comment> getAllComments() throws DaoException {
        return userCommentDao.getAllComment();
    }
}
