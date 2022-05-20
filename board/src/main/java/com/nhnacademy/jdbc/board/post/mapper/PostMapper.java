package com.nhnacademy.jdbc.board.post.mapper;

import com.nhnacademy.jdbc.board.post.domain.Post;
import com.nhnacademy.jdbc.board.post.domain.PostVoAboutDetailDown;
import com.nhnacademy.jdbc.board.post.domain.PostVoAboutDetailUp;
import com.nhnacademy.jdbc.board.post.domain.PostVoAboutList;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostMapper {
    Optional<PostVoAboutDetailUp> findById(@Param("postNo") Long postNo);

    Optional<PostVoAboutDetailUp> findByIdUp(@Param("postNo") Long postNo);

    List<PostVoAboutDetailDown> findByIdDown(@Param("postNo") Long postNo);

    List<PostVoAboutList> findAll();

    Long insertPost(@Param("post") Post post);

    Long deletePost(@Param("postNo") Long postNo);

    Long updatePost(Long id, @Param("title") String title, @Param("content") String content);

}
