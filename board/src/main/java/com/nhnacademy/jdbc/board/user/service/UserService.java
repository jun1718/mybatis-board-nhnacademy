package com.nhnacademy.jdbc.board.user.service;

import com.nhnacademy.jdbc.board.user.domain.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserByNo(Long id);

    Optional<User> getUserById(String id);

    List<User> getAllUser();

    void deleteUser(Long id);

    void insertUser(User user);

    void updateUser(String nickName,Long id);

    Optional<User> checkUser(String username, String userPwd);
}
