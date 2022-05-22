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
    public List<PostVoAboutList> getPostAll(String id, int page) {
        int limit = 20;
        page--;
        int offset = page * limit;

        if (id.equals("admin")) return postMapper.findAllOfAdmin(limit, offset);
        else return postMapper.findAllOfUser(limit, offset);
    }

    @Override
    @Transactional
    public Long writePost(Post post) {
        return postMapper.insertPost(post);
    }

    @Override
    @Transactional
    public Long removePostOrViewPost(Long id) {
        return postMapper.deleteOrAlive(id);
    }


    @Override
    @Transactional
    public Long modifyPost(Long postNo, String title, String content) {
        return postMapper.updatePost(postNo, title, content);
    }

    @Override
    public int pagination(String id, int page) {
        if (Objects.isNull(page) || page <= 1) return 1;

        int displayNum = 20;

        int totalContent = getTotalContent(id);

        int endPage = 0;
        if (totalContent % displayNum == 0) {
            endPage = totalContent / displayNum;
        } else {
            endPage = totalContent / displayNum + 1;
        }

        if (page > endPage) page = endPage;
        return page;
    }

    private int getTotalContent(String id) {
        int totalContent = 0;
        if (id.equals("admin")) totalContent = postMapper.getTotalContentOfAdmin();
        else totalContent = postMapper.getTotalContentOfUser();

        return totalContent;
    }
}
