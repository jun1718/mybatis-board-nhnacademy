package com.nhnacademy.jdbc.board.post.domain;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Post {
    private final Long postNo;
    private final Long postNoAbove;
    private final Long userNoWriter;
    private final Long userNoModifier;


    private final LocalDateTime writedAt;
    private final LocalDateTime modifiedAt;

    private final String title;
    private final String content;
    private final Long viewActivate;
}
