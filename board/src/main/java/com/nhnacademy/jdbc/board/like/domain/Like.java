package com.nhnacademy.jdbc.board.like.domain;

import lombok.Data;

@Data
public class Like {
    private final Long likeNo;
    private final Long postNo;
    private final Long userNo;
}
