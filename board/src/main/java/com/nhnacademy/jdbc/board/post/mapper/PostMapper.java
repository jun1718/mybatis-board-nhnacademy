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

    List<PostVoAboutList> findAllOfAdmin(@Param("limit") int limit, @Param("offset") int offset);
    int getTotalContentOfAdmin();
    List<PostVoAboutList> findAllOfUser(@Param("limit") int limit, @Param("offset") int offset);
    int getTotalContentOfUser();

    Optional<Post> findPostFileName(@Param("postNo") Long postNo);

    Long insertPost(@Param("post") Post post);

    Long deleteOrAlive(@Param("postNo") Long postNo);

    Long updatePost(@Param("postNo") Long postNo, @Param("title") String title, @Param("content") String content);

}
