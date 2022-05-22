package com.nhnacademy.jdbc.board.post.domain;

import java.util.Date;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
public class Post {
    private final Long postNo;
    private final Long postNoAbove;
    private final Long userNoWriter;
    private final Long userNoModifier;
    @DateTimeFormat
    private final Date writedAt;
    @DateTimeFormat
    private final Date modifiedAt;
    @Max(100)
    @NotNull
    private final String title;
    @Max(100)
    @NotNull
    private final String content;
    @NotNull
    private final Boolean viewActivate;
    private final String fileName;

}
