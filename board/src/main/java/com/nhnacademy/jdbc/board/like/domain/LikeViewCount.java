package com.nhnacademy.jdbc.board.like.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.apache.ibatis.annotations.ConstructorArgs;

@AllArgsConstructor
@Getter
public class LikeViewCount {
    private final String part;
    private final Long postNo;
    private final Long userNo;
}
