package com.nhnacademy.jdbc.board.post.domain;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
public class PostVoAboutDetailDown {
    @NotNull
    private final String userId;
    @NotNull
    private final String nickname;
    @Max(100)
    @NotNull
    private final String comment;
}
