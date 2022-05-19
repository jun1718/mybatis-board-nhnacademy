package com.nhnacademy.jdbc.board.post.mapper;

import com.nhnacademy.jdbc.board.post.domain.Post;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostMapper {
    Optional<Post> findById(@Param("postNo") Long postNo);

    List<Post> findAll();

    Long insertPost(Post post);

    Long deletePost(@Param("postNo") Long postNo);

    Long updatePost(Long id, @Param("title") String title, @Param("content") String content);
}
