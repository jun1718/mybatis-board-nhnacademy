package com.nhnacademy.jdbc.board.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.nhnacademy.jdbc.board.post.domain.Post;
import com.nhnacademy.jdbc.board.post.domain.PostVoAboutDetailUp;
import com.nhnacademy.jdbc.board.post.service.PostService;
import com.nhnacademy.jdbc.board.post.service.impl.DefaultPostService;
import com.nhnacademy.jdbc.board.user.domain.User;
import com.nhnacademy.jdbc.board.user.service.UserService;
import com.nhnacademy.jdbc.board.user.service.impl.DefaultUserService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@Slf4j
class PostInsertControllerTest {
    MockMvc mockMvc;
    PostService postService;
    UserService userService;
    MockHttpSession httpSession;
    MockMultipartFile file;
    User user;

    @BeforeEach
    void setUp() throws IOException {
        postService = mock(DefaultPostService.class);
        userService = mock(DefaultUserService.class);

         file = new MockMultipartFile(
                "hello",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );
        httpSession = new MockHttpSession();
        PostInsertController postInsertController = new PostInsertController(postService, userService);
        user = new User(1L, "a", "a", null);
        mockMvc = MockMvcBuilders.standaloneSetup(postInsertController).build();
    }

    @Test
    void requestGetUrlFailTest() throws Exception {
        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
        httpServletRequest.setRequestURI("/insert");
        mockMvc.perform(get("/insert", "insertReply")
                        .param("postNo", String.valueOf(1L)))
                .andExpect(status().isOk())
                .andExpect(view().name("showInsertForm"));
    }

    @Test
    void insertGetFailTest() throws Exception {
        PostVoAboutDetailUp postUp = new PostVoAboutDetailUp(1L, "a", "a",
                "f", "f", null, null, null, null, 6L, null);

        when(postService.getPostUp(1L)).thenReturn(Optional.of(postUp));
        mockMvc.perform(get("/insert", "insertReply")
                        .param("postNo", String.valueOf(6L)))
                .andExpect(status().isOk())
                .andExpect(view().name("showInsertForm"))
                .andExpect(model().attribute("postNoAbove", postUp.getPostNoAbove()));
    }


    @Test
    void insertFailShowInsertPostForm() throws Exception {
        httpSession.setAttribute("id", user.getId());
        when(userService.getUserById(any())).thenReturn(Optional.empty());

        MvcResult result = mockMvc.perform(post("/insert")
                        .param("title", "title")
                        .param("content", "content")
                        .session(httpSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/insert"))
                .andReturn();

        assertThat(result.getResponse().getRedirectedUrl()).isEqualTo("/insert");
        verify(userService, times(1)).getUserById(any());
    }


    @Test
    void insertSuccessShowInsertPostForm() throws Exception {
        when(postService.writePost(any())).thenReturn(1L);
        when(userService.getUserById(any())).thenReturn(Optional.of(user));

        httpSession.setAttribute("id", user.getId());
        MvcResult result = mockMvc.perform(multipart("/insert")
                        .file("file",file.getBytes())
                        .param("title", "title")
                        .param("content", "content")
                        .session(httpSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/showPosts"))
                .andReturn();

        assertThat(result.getResponse().getRedirectedUrl()).isEqualTo("/showPosts");
        verify(postService, times(1)).writePost(any());
    }


}