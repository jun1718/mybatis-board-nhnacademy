package com.nhnacademy.jdbc.board.user.mapper;

import com.nhnacademy.jdbc.board.post.domain.Post;
import com.nhnacademy.jdbc.board.user.domain.User;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    Optional<User> findUser(@Param("userNo") Long userNo);

    List<User> findAll();

    Long deleteUser(@Param("userNo") Long userNo);

    Long insertUser(User user);

    Long updateUser(@Param("nickName") String nickName,@Param("userNo") Long userNo);

    Optional<User> compareUser(@Param("id") String id,@Param("pwd")  String pwd);
}
