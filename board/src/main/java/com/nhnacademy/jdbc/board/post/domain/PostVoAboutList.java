package com.nhnacademy.jdbc.board.post.domain;

import java.sql.Timestamp;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PostVoAboutList {
    @NotNull
    private final Long postNo;
    @Size(max = 100)
    @NotNull
    private final String postTitle;
    @Size(max = 10)
    @NotNull
    private final String writerId;
    @Size(max = 10)
    @NotNull
    private final String writerNickname;
    private final String modifierId;
    private final String modifierNickname;
    @DateTimeFormat
    private final Date postWritedAt;
    private final Long viewCount;
    @NotNull
    private final int viewActivate;
}
