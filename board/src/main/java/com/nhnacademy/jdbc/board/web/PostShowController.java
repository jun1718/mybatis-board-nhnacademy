package com.nhnacademy.jdbc.board.web;

import com.nhnacademy.jdbc.board.post.domain.PostVoAboutDetailDown;
import com.nhnacademy.jdbc.board.post.domain.PostVoAboutDetailUp;
import com.nhnacademy.jdbc.board.post.domain.PostVoAboutList;
import com.nhnacademy.jdbc.board.post.service.Pagination;
import com.nhnacademy.jdbc.board.post.service.PostService;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PostShowController {
    private final PostService postService;

    public PostShowController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/showPosts")
    public ModelAndView getPosts(@RequestParam(value = "page", required = false) int page,
                                 HttpSession session) {
        String id = (String) session.getAttribute("id");

        Pagination pagination = new Pagination(20, page, postService.getTotalContent(id));
        page = pagination.getNowPage();
        session.setAttribute("page", page);

        List<PostVoAboutList> posts = postService.getPostAll(id, page);

        ModelAndView mav = new ModelAndView("showPostsForm");
        mav.addObject("posts", posts);
        return mav;
    }

    @GetMapping("/showPost")
    public ModelAndView getPost(@RequestParam("postNo") Long postNo) {
        PostVoAboutDetailUp postUp = postService.getPostUp(postNo).orElse(null);
        List<PostVoAboutDetailDown> postDown = postService.getPostDown(postNo);

        ModelAndView mav = new ModelAndView("showPostForm");
        mav.addObject("postUp", postUp);
        mav.addObject("postDown", postDown);
        return mav;
    }
}
