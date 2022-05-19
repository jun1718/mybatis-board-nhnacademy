package com.nhnacademy.jdbc.board.post.service.impl;

import com.nhnacademy.jdbc.board.post.domain.Post;
import com.nhnacademy.jdbc.board.post.mapper.PostMapper;
import com.nhnacademy.jdbc.board.post.service.PostService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class DefaultPostService implements PostService {
    private final PostMapper postMapper;

    public DefaultPostService(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Override
    public Optional<Post> getPost(Long id) {
        return postMapper.findById(id);
    }

    @Override
    public List<Post> getPostAll() {
        return postMapper.findAll();
    }

    @Override
    public Long writePost(Post post) {
        return postMapper.insertPost(post);
    }

    @Override
    public Long removePost(Long id) {
        return postMapper.deletePost(id);
    }

    @Override
    public Long modifyPost(Long id, String title, String content) {
        return postMapper.updatePost(id, title, content);
    }
}
