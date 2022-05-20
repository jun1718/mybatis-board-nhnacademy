package com.nhnacademy.jdbc.board.post.domain;

import lombok.Data;

@Data
public class PostVoAboutDetailDown {
    private final String userId;
    private final String nickname;
    private final String comment;
}
