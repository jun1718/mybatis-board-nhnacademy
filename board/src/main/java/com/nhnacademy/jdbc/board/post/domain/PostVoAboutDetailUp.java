package com.nhnacademy.jdbc.board.post.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor
@Getter
public class PostVoAboutDetailUp {
    private final Long postNo;
    private final String postTitle;
    private final String postContent;
    private final String writerId;
    private final String writerNickname;
    private final String modifierId;
    private final String modifierNickname;
    private final Date postWritedAt;
    private final Date postModifiedAt;
    private final Long postNoAbove;
}
