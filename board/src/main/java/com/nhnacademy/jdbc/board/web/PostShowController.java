package com.nhnacademy.jdbc.board.web;

import com.nhnacademy.jdbc.board.post.domain.PostVoAboutDetailDown;
import com.nhnacademy.jdbc.board.post.domain.PostVoAboutDetailUp;
import com.nhnacademy.jdbc.board.post.domain.PostVoAboutList;
import com.nhnacademy.jdbc.board.post.service.PostService;
import java.util.List;
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
    public ModelAndView getPosts() {
        List<PostVoAboutList> posts = postService.getPostAll();
        ModelAndView mav = new ModelAndView("showPosts");
        mav.addObject("posts", posts);
        return mav;
    }

    @GetMapping("/showPost")
    public ModelAndView getPost(@RequestParam("id") Long id) {
        PostVoAboutDetailUp postUp = postService.getPostUp(id).orElse(null);
        List<PostVoAboutDetailDown> postDown = postService.getPostDown(id);

        ModelAndView mav = new ModelAndView("showPost");
        mav.addObject("postUp", postUp);
        mav.addObject("postDown", postDown);
        return mav;
    }
}
