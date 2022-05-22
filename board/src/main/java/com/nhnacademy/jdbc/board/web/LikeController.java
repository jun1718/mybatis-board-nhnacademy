package com.nhnacademy.jdbc.board.web;

import com.nhnacademy.jdbc.board.like.domain.Like;
import com.nhnacademy.jdbc.board.like.service.LikeService;
import com.nhnacademy.jdbc.board.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class LikeController {
    private final LikeService likeService;
    private final UserService userService;
    public LikeController(LikeService likeService, UserService userService) {
        this.likeService = likeService;
        this.userService = userService;
    }

    @GetMapping("/like")
    public String checklike(@RequestParam("postNo") Long postNo,
                           HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(Objects.isNull(session)){
            return "redirect:/showPosts";
        }
        String userId = (String) session.getAttribute("id");
        Like like = new Like(null, postNo, userService.getUserById(userId).get().getUserNo());

        return "redirect:/showPosts";
    }
}
