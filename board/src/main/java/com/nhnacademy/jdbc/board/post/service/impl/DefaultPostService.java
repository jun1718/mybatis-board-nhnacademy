package com.nhnacademy.jdbc.board.post.service.impl;

import com.nhnacademy.jdbc.board.post.domain.Post;
import com.nhnacademy.jdbc.board.post.domain.PostVoAboutDetailDown;
import com.nhnacademy.jdbc.board.post.domain.PostVoAboutDetailUp;
import com.nhnacademy.jdbc.board.post.domain.PostVoAboutList;
import com.nhnacademy.jdbc.board.post.mapper.PostMapper;
import com.nhnacademy.jdbc.board.post.service.PostService;
import java.util.List;
import java.util.Objects;
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
        return null;
    }

    @Override
    public List<PostVoAboutList> getPostAll(int page) {
        page -= 1;
        int limit = 20;
        int offset = --page;

        return postMapper.findAll(limit, offset);
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
    public Long modifyPost(Long postNo, String title, String content) {
        return postMapper.updatePost(postNo, title, content);
    }

    @Override
    public int pagination(int page) {
        if (Objects.isNull(page) || page <= 1) return 1;

        int displayNum = 20;
        int totalContent = getTotalContent();
        int endPage = 0;
        if (totalContent % displayNum == 0) {
            endPage = totalContent / displayNum;
        } else {
            endPage = totalContent / displayNum + 1;
        }

        if (page > endPage) page = endPage;
        return page;
    }

    private int getTotalContent() {
        return postMapper.getTotalContent();
    }
}
