package com.nhnacademy.jdbc.board.comment.service;

import com.nhnacademy.jdbc.board.comment.domain.Comment;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Param;

public interface CommentService {
    Optional<Comment> findComment(Long id);

    List<Comment> findAllComment();

    Long insertComment(Comment comment);

    Long removeComment(Long id);

    Long modifyComment(Long id, String commentContent);
}
