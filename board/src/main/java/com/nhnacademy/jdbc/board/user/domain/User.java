package com.nhnacademy.jdbc.board.user.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class User {
    private final Long userNo;
    @Size(max = 10)
    @NotNull
    private final String id;
    @NotNull
    @Size(max = 20)
    private final String pwd;
    @NotNull
    @Size(max = 10)
    private final String nickname;
}
