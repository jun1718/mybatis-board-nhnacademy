package com.nhnacademy.jdbc.board.like.service;

import com.nhnacademy.jdbc.board.like.domain.Like;

import java.util.List;

public interface LikeService {
    List<Like> checkUserLikePostNo(Long userNo);

    List<Like> findAllLike();

    Long createLike(Like like);

    Long removeLike(Long likeNo);
}
