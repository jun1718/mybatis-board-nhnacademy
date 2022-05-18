package com.nhnacademy.jdbc.board.post.domain;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Post {
    private final Long postNo;
    private final Long postUserNo;
    private final Long postReplyNo;
    private final String postTitle;
    private final String postContent;
    private final LocalDateTime postCreatedAt;
    private final Long postReplyCount;
}
