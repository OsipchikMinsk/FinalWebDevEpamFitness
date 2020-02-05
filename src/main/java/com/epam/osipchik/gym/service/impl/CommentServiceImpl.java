package com.epam.osipchik.gym.service.impl;

import com.epam.osipchik.gym.dao.UserCommentDao;
import com.epam.osipchik.gym.dao.impl.DaoException;
import com.epam.osipchik.gym.dao.impl.DaoFactory;
import com.epam.osipchik.gym.entity.comment.Comment;
import com.epam.osipchik.gym.service.CommentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);
    private UserCommentDao userCommentDao = DaoFactory.getInstance().getUserCommentDao();

    @Override
    public Comment postComment(Comment comment) throws ServiceException {
        Comment commentService;
        try {
            commentService = userCommentDao.createComment(comment);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return commentService;
    }

    @Override
    public boolean removeComment(Comment comment) throws ServiceException {
        boolean result;
        try {
            result = userCommentDao.deleteComment(comment);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public List<Comment> getAllComments() throws ServiceException {
        List<Comment> allComments;
        try {
            allComments = userCommentDao.getAllComment();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return allComments;
    }
}
