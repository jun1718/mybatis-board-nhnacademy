package com.nhnacademy.jdbc.board.like.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Like {
    @NotNull
    @Size(max = 10)
    private final String part;
    private final Long postNo;
    private final Long userNo;
}
