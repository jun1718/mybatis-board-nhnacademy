package com.nhnacademy.jdbc.board.user.mapper;

import com.nhnacademy.jdbc.board.user.domain.User;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    Optional<User> findUser(Long id);

    List<User> findAll();

    Long deleteUser(Long id);

    Long insertUser(User user);

    Long updateUser(String nickName,Long id);

    Optional<User> compareUser(@Param("userId") String userId,@Param("userPwd")  String userPwd);
}
