package com.nhnacademy.jdbc.board.post.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Post {
    private final Long postNo;
    private final Long postNoAbove;
    private final Long userNoWriter;
    private final Long userNoModifier;


    private final Date writedAt;
    private final Date modifiedAt;

    private final String title;
    private final String content;
    private final Boolean viewActivate;
    private final String fileName;
}
