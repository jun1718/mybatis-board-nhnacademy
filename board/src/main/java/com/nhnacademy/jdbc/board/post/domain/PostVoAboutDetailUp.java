package com.nhnacademy.jdbc.board.post.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class PostVoAboutDetailUp {
    @NotNull
    private final Long postNo;
    @Size(max = 100)
    @NotNull
    private final String postTitle;
    @Size(max = 100)
    @NotNull
    private final String postContent;
    @NotNull
    private final String writerId;
    @NotNull
    private final String writerNickname;
    private final String modifierId;
    private final String modifierNickname;
    @DateTimeFormat
    @NotNull
    private final Date postWritedAt;
    @DateTimeFormat
    private final Date postModifiedAt;
    private final Long postNoAbove;
    private final String fileName;
}
