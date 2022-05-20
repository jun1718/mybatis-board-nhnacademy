package com.nhnacademy.jdbc.board.post.service.impl;

import com.nhnacademy.jdbc.board.post.domain.Post;
import com.nhnacademy.jdbc.board.post.domain.PostVoAboutDetailDown;
import com.nhnacademy.jdbc.board.post.domain.PostVoAboutDetailUp;
import com.nhnacademy.jdbc.board.post.domain.PostVoAboutList;
import com.nhnacademy.jdbc.board.post.mapper.PostMapper;
import com.nhnacademy.jdbc.board.post.service.PostService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DefaultPostService implements PostService {
    private final PostMapper postMapper;

    public DefaultPostService(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Override
    public Optional<PostVoAboutDetailUp> getPostUp(Long id) {
        return postMapper.findByIdUp(id);
    }

    @Override
    public List<PostVoAboutDetailDown> getPostDown(Long id) {
        return postMapper.findByIdDown(id);
    }

    @Override
    public List<PostVoAboutList> getPostAll() {
        return postMapper.findAll();
    }

    @Override
    public Long writePost(Post post) {
        return postMapper.insertPost(post);
    }

    @Override
    public Long removePostOrViewPost(Long id) {
        return postMapper.deleteOrAlive(id);
    }


    @Override
    public Long modifyPost(Long id, String title, String content) {
        return postMapper.updatePost(id, title, content);
    }
}
