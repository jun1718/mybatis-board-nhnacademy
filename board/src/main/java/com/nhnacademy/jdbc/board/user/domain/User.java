package com.nhnacademy.jdbc.board.user.domain;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class User {
    private final Long id;
    private final String userId;
    private final String userPwd;
    private final String nickName;
}
