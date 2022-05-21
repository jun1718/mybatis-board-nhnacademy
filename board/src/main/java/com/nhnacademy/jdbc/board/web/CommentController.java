package com.nhnacademy.jdbc.board.web;

import com.nhnacademy.jdbc.board.comment.domain.Comment;
import com.nhnacademy.jdbc.board.comment.service.CommentService;
import com.nhnacademy.jdbc.board.post.service.PostService;
import com.nhnacademy.jdbc.board.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;
    private final PostService postService;
    public CommentController(CommentService commentService, UserService userService, PostService postService) {
        this.commentService = commentService;
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/comment")
    public ModelAndView getComment(@RequestParam("postNo") Long postNo,
                             @RequestParam("userId") String userId){
        ModelAndView mav =new ModelAndView("commentInsertForm");
        Long userNo = userService.getUserById(userId).get().getUserNo();

        mav.addObject("postNo", postNo);
        mav.addObject("userNo", userNo);
        return mav;
    }

    @PostMapping("/comment")
    public String doComment(@RequestParam("content") String content,
                            @RequestParam("postNo") Long postNo,
                            @RequestParam("userNo") Long userNo){

        if(content.isEmpty()){
            return "redirect:/comment";
        }
        Comment comment = new Comment(null, postNo,userNo, content);
        commentService.insertComment(comment);

        return "redirect:/showPost?postNo="+postNo;
    }
}
