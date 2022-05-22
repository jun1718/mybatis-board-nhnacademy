package com.nhnacademy.jdbc.board.like.mapper;

import com.nhnacademy.jdbc.board.like.domain.LikeViewCount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LikeMapper {

    List<LikeViewCount> findLikePostNo(@Param("userNo") Long userNo);

    Integer getCountLikeViewCount(@Param("postNo") Long postNo, @Param("part") String part);

    Long insertLike(@Param("like") LikeViewCount like);

    LikeViewCount getLikeViewCount(@Param("like") LikeViewCount like);

    void deleteLikeViewCount(@Param("like") LikeViewCount like);

    List<LikeViewCount> getAllLikeViewCountByUserId(@Param("userNo") Long userNo);
}
