package com.nhnacademy.jdbc.board.comment.domain;

import lombok.Data;

@Data
public class Comment {
    private final Long commentNo;
    private final Long postNo;
    private final String commentContent;
}
