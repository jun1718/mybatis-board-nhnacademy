package com.nhnacademy.jdbc.board.web;

import com.nhnacademy.jdbc.board.exception.ValidationFailedException;
import com.nhnacademy.jdbc.board.post.domain.Post;
import com.nhnacademy.jdbc.board.post.domain.PostRegisterRequest;
import com.nhnacademy.jdbc.board.post.domain.PostReplyRequset;
import com.nhnacademy.jdbc.board.post.domain.PostVoAboutDetailUp;
import com.nhnacademy.jdbc.board.post.service.PostService;
import com.nhnacademy.jdbc.board.user.domain.User;
import com.nhnacademy.jdbc.board.user.service.UserService;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.ValidationException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class PostInsertController {
    private static final String UPLOAD_DIR = "D:/file/tmp";
    private final PostService postService;
    private final UserService userService;

    public PostInsertController(PostService postService,
                                UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping(value = {"/insert", "/insertReply"})
    public ModelAndView insert(HttpServletRequest request,
                               @RequestParam(value = "postNo", required = false)
                                   Long postNoAbove) {

        ModelAndView mav = new ModelAndView("showInsertForm");
        ModelAndView failMav = new ModelAndView("redirect:/showPost?postNo=" + postNoAbove);

        if (request.getRequestURI().equals("/insert")) {
            mav.addObject("isReply", false);
            return mav;
        }
        mav.addObject("postNoAbove", postNoAbove);

        int n = 1;
        Long postNo = -1L;
        while (n < 6) {
            Optional<PostVoAboutDetailUp> postUp = postService.getPostUp(postNoAbove);
            if (n == 1) {
                postNo = postUp.get().getPostNo();
            }
            Optional<Long> postNoAboveReOp = Optional.ofNullable(postUp.get().getPostNoAbove());
            postNoAbove = postNoAboveReOp.orElse(0L);

            if (postNoAbove == 0L) {
                break;
            }
            n++;
        }
        if (n >= 6) {
            return failMav;
        }
        // TODO: StringBuilder로 변경, 이름변경(표현력)
        String str = "";
        for (int i = 0; i < n; i++) {
            str += "  ";
        }
        str += "RE(" + postNo + "번 게시글의 답글) : ";

        mav.addObject("RE", str);

        mav.addObject("isReply", true);
        return mav;
}

    @PostMapping("/insert")
    public String doInsert(@Valid @ModelAttribute PostRegisterRequest postRequest,
                           BindingResult bindingResult,
                           HttpServletRequest request) throws IOException {
        log.warn("------------------------------------------");
        log.warn("------------------------------------------");
        log.warn("------------------------------------------");
        if(bindingResult.hasErrors()) {
            log.error("" + bindingResult);
            throw new ValidationFailedException(bindingResult);
        }
        if(postRequest.getContent().isEmpty() || postRequest.getTitle().isEmpty()){
            throw new IllegalArgumentException("빈값을 입력하셨습니다.");
        }

        log.error("postRequset"+postRequest.toString());
        checkfile(postRequest.getFile());
        HttpSession session = request.getSession(false);
        String attribute = (String) session.getAttribute("id");
        Optional<User> optionalUser = userService.getUserById(attribute);
        if (optionalUser.isEmpty()) {
            return "redirect:/insert";
        }
        postService.writePost(new Post(
            null, null, optionalUser.get().getUserNo(), null,
            new Date(), null, postRequest.getTitle(), postRequest.getContent(), true, postRequest.getFile().getOriginalFilename()));
        return "redirect:/showPosts?page=" + request.getSession().getAttribute("page");
    }

    @PostMapping("/insertReply")
    public String doInsertReply(@Valid @ModelAttribute PostReplyRequset postReplyReq,
                                BindingResult bindingResult,
                                HttpServletRequest request) throws IOException {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        checkfile(postReplyReq.getFile());
        HttpSession session = request.getSession(false);
        String attribute = (String) session.getAttribute("id");
        Optional<User> optionalUser = userService.getUserById(attribute);

        if (optionalUser.isEmpty()) {
            return "redirect:/insert";
        }
        postReplyReq.setRe(postReplyReq.getRe()+postReplyReq.getTitle());

        postService.writePost(new Post(
                null, postReplyReq.getPostNoAbove(), optionalUser.get().getUserNo(), null,
                new Date(), null, postReplyReq.getRe(), postReplyReq.getContent(), true, postReplyReq.getFile().getOriginalFilename()));
        return "redirect:/showPost?postNo=" + postReplyReq.getPostNoAbove();
    }

    private void checkfile(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            file.transferTo(Paths.get(UPLOAD_DIR + file.getOriginalFilename()));
        }
    }
}
