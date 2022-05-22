package com.nhnacademy.jdbc.board.comment.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Comment {
    private final Long commentNo;
    private final Long postNo;
    private final Long userNo;

    @Size(max = 50)
    @NotNull
    private final String content;


}
