package com.nhnacademy.jdbc.board.like.mapper;

import com.nhnacademy.jdbc.board.comment.domain.Comment;
import com.nhnacademy.jdbc.board.like.domain.Like;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface LikeMapper {

    List<Like> findLikePostNo(@Param("userNo") Long userNo);

    List<Like> findAll();

    Long insertLike(Like like);

    Long deleteLike(@Param("likeNo") Long likeNo);
}
