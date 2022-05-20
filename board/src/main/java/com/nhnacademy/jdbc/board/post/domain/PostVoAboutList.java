package com.nhnacademy.jdbc.board.post.domain;

import java.sql.Timestamp;
import java.util.Date;
import lombok.Data;

@Data
public class PostVoAboutList {
    private final Long postNo;
    private final String postTitle;
    private final String writerId;
    private final String writerNickname;
    private final String modifierId;
    private final String modifierNickname;
    private final Date postWritedAt;
    private final Long viewCount;
    private final int viewActivate;
}
