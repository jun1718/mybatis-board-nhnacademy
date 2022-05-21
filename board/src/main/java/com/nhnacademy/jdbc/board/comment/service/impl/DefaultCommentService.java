package com.nhnacademy.jdbc.board.comment.service.impl;

import com.nhnacademy.jdbc.board.comment.domain.Comment;
import com.nhnacademy.jdbc.board.comment.mapper.CommentMapper;
import com.nhnacademy.jdbc.board.comment.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultCommentService implements CommentService {
    private final CommentMapper commentMapper;

    public DefaultCommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public Optional<Comment> findComment(Long id) {
        return commentMapper.findById(id);
    }

    @Override
    public List<Comment> findAllComment() {
        return commentMapper.findAll();
    }

    @Override
    public Long insertComment(Comment comment) {
        return commentMapper.insertComment(comment);
    }

    @Override
    public Long removeComment(Long id) {
        return commentMapper.deleteComment(id);
    }

    @Override
    public Optional<Comment> modifyComment(Long id,Long userNo, String commentContent) {
        return commentMapper.updateComment(id,userNo, commentContent);
    }

}
