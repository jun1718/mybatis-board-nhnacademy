package com.nhnacademy.jdbc.board.post.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PostReplyRequset {
    @Size(max = 100,min = 2)
    @NotNull
    String title;
    @Size(max = 100,min = 1)
    @NotNull
    String content;
    MultipartFile file;
    @Min(1)
    @NotNull
    Long postNoAbove;
    @Size(max = 100,min = 1)
    @NotNull
    String re;
}
