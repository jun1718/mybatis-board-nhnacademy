package com.nhnacademy.jdbc.board.web;

import com.nhnacademy.jdbc.board.post.domain.Post;
import com.nhnacademy.jdbc.board.post.service.PostService;
import com.nhnacademy.jdbc.board.user.domain.User;
import com.nhnacademy.jdbc.board.user.service.UserService;
import java.util.Date;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class PostInsertController {
    private final PostService postService;
    private final UserService userService;

    public PostInsertController(PostService postService,
                                UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }


    @GetMapping("/insert")
    public String insert() {
        return "showInsertForm";
    }

    @PostMapping("/insert")
    public String doInsert(@RequestParam("title") String title,
                           @RequestParam("content") String content,
                           HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        String attribute = (String)session.getAttribute("id");
        if(userService.getUserById(attribute).isEmpty()
            || title.isEmpty()
            || content.isEmpty()){
            return "redirect:/insert";
        }
        User user = userService.getUserById(attribute).get();
        Long insertedResult = postService.writePost(new Post(
            null, null, user.getUserNo(), null,
                new Date(), null, title, content, true));
        return "redirect:/showPosts";
    }
}
