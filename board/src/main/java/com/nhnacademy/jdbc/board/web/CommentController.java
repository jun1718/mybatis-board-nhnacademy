package com.nhnacademy.jdbc.board.web;

import com.nhnacademy.jdbc.board.comment.domain.Comment;
import com.nhnacademy.jdbc.board.comment.service.CommentService;
import com.nhnacademy.jdbc.board.exception.ValidationFailedException;
import com.nhnacademy.jdbc.board.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.ValidationException;

@Controller
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;

    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/comment")
    public ModelAndView getComment(@RequestParam("postNo") Long postNo,
                                   @RequestParam("userId") String userId) {
        ModelAndView mav = new ModelAndView("commentInsertForm");
        Long userNo = userService.getUserById(userId).get().getUserNo();

        mav.addObject("postNo", postNo);
        mav.addObject("userNo", userNo);
        return mav;
    }

    @PostMapping("/comment")
    public String doComment(@Valid @RequestParam("content") String content,
                            BindingResult bindingResult,
                            @RequestParam("postNo") Long postNo,
                            @RequestParam("userNo") Long userNo) {
        if (bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }
        if (content.isEmpty()) {
            return "redirect:/comment";
        }
        commentService.insertComment(new Comment(null, postNo, userNo, content));

        return "redirect:/showPost?postNo=" + postNo;
    }
}
