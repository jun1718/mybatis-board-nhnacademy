package com.nhnacademy.jdbc.board.post.service;

import com.nhnacademy.jdbc.board.post.domain.Post;
import com.nhnacademy.jdbc.board.post.domain.PostVoAboutDetailDown;
import com.nhnacademy.jdbc.board.post.domain.PostVoAboutDetailUp;
import com.nhnacademy.jdbc.board.post.domain.PostVoAboutList;
import java.util.List;
import java.util.Optional;

public interface PostService {
    Optional<PostVoAboutDetailUp> getPostUp(Long id);
    List<PostVoAboutDetailDown> getPostDown(Long id);

    List<PostVoAboutList> getPostAll();

    Long writePost(Post post);

    Long removePost(Long id);

    Long modifyPost(Long id,String title,String content);
}
