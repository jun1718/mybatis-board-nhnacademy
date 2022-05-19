package com.nhnacademy.jdbc.board.user.domain;

import lombok.Data;

@Data
public class User {
    private final Long userNo;
    private final String id;
    private final String pwd;
    private final String nickName;
}
