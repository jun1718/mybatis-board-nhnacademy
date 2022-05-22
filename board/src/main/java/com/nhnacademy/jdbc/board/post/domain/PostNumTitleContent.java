package com.nhnacademy.jdbc.board.post.domain;

import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Value
public class PostNumTitleContent {
    Long postNo;
    @Size(max = 100, min = 1)
    @NotEmpty
    String postTitle;
    @Size(max = 100, min = 1)
    @NotEmpty
    String postContent;
}
