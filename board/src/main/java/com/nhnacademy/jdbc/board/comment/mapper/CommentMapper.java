package com.nhnacademy.jdbc.board.comment.mapper;

import com.nhnacademy.jdbc.board.comment.domain.Comment;
import com.nhnacademy.jdbc.board.post.domain.Post;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentMapper {
    Optional<Comment> findById(Long commentNo);

    List<Comment> findAll();

    Long insertComment(Comment comment);

    Long deleteComment(Long commentNo);

    Long updateComment(Long commentNo,Long userNo,@Param("content") String content);
}
