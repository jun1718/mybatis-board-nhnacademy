package com.nhnacademy.jdbc.board.post.service;

import com.nhnacademy.jdbc.board.post.domain.Post;
import com.nhnacademy.jdbc.board.post.domain.PostVoAboutDetailDown;
import com.nhnacademy.jdbc.board.post.domain.PostVoAboutDetailUp;
import com.nhnacademy.jdbc.board.post.domain.PostVoAboutList;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;


public interface PostService {
    Optional<PostVoAboutDetailUp> getPostUp(Long id);
    List<PostVoAboutDetailDown> getPostDown(Long id);

    List<PostVoAboutList> getPostAll(String id, int page);

    Long writePost(Post post);

    Long removePostOrViewPost(Long id);

    Long modifyPost(Long id,String title,String content);

    Optional<Post> findFileName( Long postNo);

    int getTotalContent(String id);

    List<PostVoAboutList> getLikedPostAll(String id, int page);
}
