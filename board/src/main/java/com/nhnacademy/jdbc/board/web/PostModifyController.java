package com.nhnacademy.jdbc.board.web;

import com.nhnacademy.jdbc.board.post.domain.Post;
import com.nhnacademy.jdbc.board.post.domain.PostVoAboutDetailUp;
import com.nhnacademy.jdbc.board.post.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PostModifyController {
    private final PostService postService;

    public PostModifyController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/modify")
    public ModelAndView modify(@RequestParam("postNo") Long postNo) {
        ModelAndView mav = new ModelAndView("showModifyForm");
        PostVoAboutDetailUp postUp = postService.getPostUp(postNo).get();
        mav.addObject("postUp", postUp);
        return mav;
    }

    @PostMapping("/modify")
    public String doModify() {

        return "redirect:/showPostsForm";
    }
}
