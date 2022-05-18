package com.nhnacademy.jdbc.board.comment.mapper;

import com.nhnacademy.jdbc.board.comment.domain.Comment;
import com.nhnacademy.jdbc.board.post.domain.Post;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentMapper {
    Optional<Comment> findById(Long id);

    List<Comment> findAll();

    Long insertPost(Comment comment);

    Long deletePost(Long id);

    Long updatePost(Long id,@Param("commentContent") String commentContent);
}
