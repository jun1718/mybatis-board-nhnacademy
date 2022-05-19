package com.nhnacademy.jdbc.board.comment.service;

import com.nhnacademy.jdbc.board.comment.domain.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<Comment> findComment(Long id);

    List<Comment> findAllComment();

    Long insertComment(Comment comment);

    Long removeComment(Long id);

    Optional<Comment> modifyComment(Long id,Long userNo,String content);
}
