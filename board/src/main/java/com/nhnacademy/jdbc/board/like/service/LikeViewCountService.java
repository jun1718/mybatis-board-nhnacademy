package com.nhnacademy.jdbc.board.like.service;

import com.nhnacademy.jdbc.board.like.domain.LikeViewCount;

import java.util.List;

public interface LikeViewCountService {
    List<LikeViewCount> checkUserLikePostNo(Long userNo);

    Long getCountLikeViewCount(Long postNo, String part);

    Long createLike(LikeViewCount like);

    LikeViewCount getLikeViewCount(LikeViewCount like);

    void deleteLikeViewCount(LikeViewCount like);

    List<LikeViewCount> getAllLikeViewCountByUserNo(Long userNo);
}
