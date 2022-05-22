package com.nhnacademy.jdbc.board.post.domain;

import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Value
public class PostRegisterRequest {
//    @Size(max = 100,min = 1)
    @NotNull
    @NotEmpty
    @NotBlank
    String title;

    //    @Size(max = 100,min = 1)
    @NotEmpty
    @NotNull
    @NotBlank
    String content;

    MultipartFile file;
}
