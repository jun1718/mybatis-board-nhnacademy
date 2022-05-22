package com.nhnacademy.jdbc.board.web;

import com.nhnacademy.jdbc.board.like.domain.LikeViewCount;
import com.nhnacademy.jdbc.board.like.service.LikeViewCountService;
import com.nhnacademy.jdbc.board.post.domain.PostVoAboutDetailDown;
import com.nhnacademy.jdbc.board.post.domain.PostVoAboutDetailUp;
import com.nhnacademy.jdbc.board.post.domain.PostVoAboutList;
import com.nhnacademy.jdbc.board.post.service.Pagination;
import com.nhnacademy.jdbc.board.post.service.PostService;
import com.nhnacademy.jdbc.board.user.service.UserService;
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
    private final LikeViewCountService likeViewCountService;
    private final UserService userService;

    public PostShowController(PostService postService, LikeViewCountService likeViewCountService,
                              UserService userService) {
        this.postService = postService;
        this.likeViewCountService = likeViewCountService;
        this.userService = userService;
    }

    @GetMapping("/showPosts")
    public ModelAndView getPosts(@RequestParam(value = "page", required = false) int page,
                                 HttpSession session) {
        String id = (String) session.getAttribute("id");

        Pagination pagination = new Pagination(20, page, postService.getTotalContent(id));
        page = pagination.getNowPage();
        session.setAttribute("page", page);
        session.setAttribute("endPage", pagination.getEndPage());

        List<PostVoAboutList> posts = postService.getPostAll(id, page);


        ModelAndView mav = new ModelAndView("showPostsForm");
        mav.addObject("posts", posts);
        return mav;
    }

    @GetMapping("/showPost")
    public ModelAndView getPost(@RequestParam("postNo") Long postNo,
                                HttpSession session) {
        PostVoAboutDetailUp postUp = postService.getPostUp(postNo).orElse(null);
        List<PostVoAboutDetailDown> postDown = postService.getPostDown(postNo);

        ModelAndView mav = new ModelAndView("showPostForm");
        mav.addObject("postUp", postUp);
        mav.addObject("postDown", postDown);

        String userId = (String) session.getAttribute("id");
        LikeViewCount like = new LikeViewCount("좋아요", postNo, userService.getUserById(userId).get().getUserNo());
        LikeViewCount likeViewCount = likeViewCountService.getLikeViewCount(like);
        if (!Objects.isNull(likeViewCount)) {
            mav.addObject("isLike", true);
            return mav;
        }

        mav.addObject("isLike", false);
        return mav;
    }

    @GetMapping("/showLikedPosts")
    public ModelAndView showLikedPosts(@RequestParam(value = "page", required = false) int page,
                                 @RequestParam("postNo") Long postNo,
                                 HttpSession session) {
        String userId = (String) session.getAttribute("id");

        Pagination pagination = new Pagination(20, page, postService.getTotalContent(userId));
        page = pagination.getNowPage();
        session.setAttribute("page", page);
        session.setAttribute("endPage", pagination.getEndPage());

        Long userNo = userService.getUserById(userId).get().getUserNo();
        List<LikeViewCount> likes = likeViewCountService.getAllLikeViewCountByUserNo(userNo);
        StringBuilder sb = new StringBuilder();
        for (LikeViewCount like : likes) {
            sb.append("")
                .append(like.getPostNo());
        }
        ModelAndView mav = new ModelAndView("showPostsForm");
        List<PostVoAboutList> posts = postService.getLikedPostAll(userId, page);
//        mav.addObject("posts", posts);
        return mav;
    }
}
