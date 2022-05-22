package com.nhnacademy.jdbc.board.like.service.impl;

import com.nhnacademy.jdbc.board.like.domain.LikeViewCount;
import com.nhnacademy.jdbc.board.like.mapper.LikeMapper;
import com.nhnacademy.jdbc.board.like.service.LikeViewCountService;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultLikeViewCountService implements LikeViewCountService {
    private final LikeMapper likeMapper;

    public DefaultLikeViewCountService(LikeMapper likeMapper) {
        this.likeMapper = likeMapper;
    }

    @Override
    public List<LikeViewCount> checkUserLikePostNo(Long userNo) {
        return likeMapper.findLikePostNo(userNo);
    }

    @Override
    public Long getCountLikeViewCount(Long postNo, String part){
        likeMapper.getCountLikeViewCount(postNo, part);
        return 1L;
    }

    @Override
    @Transactional
    public Long createLike(LikeViewCount like) {
        return likeMapper.insertLike(like);
    }

    @Override
    public LikeViewCount getLikeViewCount(LikeViewCount like) {
        return likeMapper.getLikeViewCount(like);
    }

    @Override
    public void deleteLikeViewCount(LikeViewCount like) {
        likeMapper.deleteLikeViewCount(like);
    }

    @Override
    public List<LikeViewCount> getAllLikeViewCountByUserNo(Long userNo) {
        return likeMapper.getAllLikeViewCountByUserId(userNo);
    }

}
