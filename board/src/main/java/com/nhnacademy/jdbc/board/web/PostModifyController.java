package com.nhnacademy.jdbc.board.web;

import com.nhnacademy.jdbc.board.exception.ValidationFailedException;
import com.nhnacademy.jdbc.board.post.domain.Post;
import com.nhnacademy.jdbc.board.post.domain.PostNumTitleContent;
import com.nhnacademy.jdbc.board.post.domain.PostVoAboutDetailUp;
import com.nhnacademy.jdbc.board.post.service.PostService;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String doModify(@Valid @ModelAttribute PostNumTitleContent post,
                           BindingResult bindingResult,
                           HttpSession session) {
        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }
        if(post.getPostContent().isEmpty() || post.getPostTitle().isEmpty()){
            throw new IllegalArgumentException("빈값을 입력하셨습니다.");
        }
        postService.modifyPost(post.getPostNo(), post.getPostTitle(), post.getPostContent());
        return "redirect:/showPosts?page=" + session.getAttribute("page");
    }
}
