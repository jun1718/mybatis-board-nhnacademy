package com.nhnacademy.jdbc.board.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
    @GetMapping("/showPosts")
    public String getPosts() {

        return "showPosts";
    }

}
