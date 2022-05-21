package com.nhnacademy.jdbc.board.web;

import com.nhnacademy.jdbc.board.post.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PostDeleteOrAliveController {
    private final PostService postService;

    public PostDeleteOrAliveController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/deleteAndAlive")
    public ModelAndView delete(@RequestParam("postNo") Long postNo){
        ModelAndView mav = new ModelAndView("redirect:/showPosts");
        postService.removePostOrViewPost(postNo);

        return mav;
    }

}
