package com.nhnacademy.jdbc.board.web;

import com.nhnacademy.jdbc.board.like.domain.LikeViewCount;
import com.nhnacademy.jdbc.board.like.service.LikeViewCountService;
import com.nhnacademy.jdbc.board.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class LikeController {
    private final LikeViewCountService likeViewCountService;
    private final UserService userService;
    public LikeController(LikeViewCountService likeService, UserService userService) {
        this.likeViewCountService = likeService;
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
        LikeViewCount like = new LikeViewCount("좋아요", postNo, userService.getUserById(userId).get().getUserNo());
        LikeViewCount likeViewCount = likeViewCountService.getLikeViewCount(like);
        if (!Objects.isNull(likeViewCount)) {
            return "redirect:/showPost?postNo=" + postNo;
        }

        likeViewCountService.createLike(like);
        return "redirect:/showPost?postNo=" + postNo;
    }

    @GetMapping("/likeDelete")
    public String likeDelete(@RequestParam("postNo") Long postNo,
                             HttpSession session) {
        LikeViewCount like = new LikeViewCount("좋아요", postNo, userService.getUserById(
            (String) session.getAttribute("id")).get().getUserNo());
        LikeViewCount likeViewCount = likeViewCountService.getLikeViewCount(like);
        if (Objects.isNull(likeViewCount)) {
            return "redirect:/showPost?postNo=" + postNo;
        }

        likeViewCountService.deleteLikeViewCount(like);
        return "redirect:/showPost?postNo=" + postNo;
    }

}
