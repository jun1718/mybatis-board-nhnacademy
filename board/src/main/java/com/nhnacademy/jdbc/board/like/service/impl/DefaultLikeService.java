package com.nhnacademy.jdbc.board.like.service.impl;

import com.nhnacademy.jdbc.board.like.domain.Like;
import com.nhnacademy.jdbc.board.like.mapper.LikeMapper;
import com.nhnacademy.jdbc.board.like.service.LikeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultLikeService implements LikeService {
    private final LikeMapper likeMapper;

    public DefaultLikeService(LikeMapper likeMapper) {
        this.likeMapper = likeMapper;
    }

    @Override
    public List<Like> checkUserLikePostNo(Long userNo) {
        return likeMapper.findLikePostNo(userNo);
    }

    @Override
    public List<Like> findAllLike() {
        return likeMapper.findAll();
    }

    @Override
    public Long createLike(Like like) {
        return likeMapper.insertLike(like);
    }

    @Override
    public Long removeLike(Long likeNo) {
        return likeMapper.deleteLike(likeNo);
    }
}
