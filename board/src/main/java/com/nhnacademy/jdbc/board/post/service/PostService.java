package com.nhnacademy.jdbc.board.post.service;

import com.nhnacademy.jdbc.board.post.domain.Post;
import java.util.List;
import java.util.Optional;

public interface PostService {
    Optional<Post> getPost(Long id);

    List<Post> getPostAll();

    Long writePost(Post post);

    Long removePost(Long id);

    Long modifyPost(Long id,String title,String content);
}
