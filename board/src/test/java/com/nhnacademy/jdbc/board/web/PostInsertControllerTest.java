package com.nhnacademy.jdbc.board.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.jdbc.board.post.domain.Post;
import com.nhnacademy.jdbc.board.post.service.PostService;
import com.nhnacademy.jdbc.board.post.service.impl.DefaultPostService;
import com.nhnacademy.jdbc.board.user.domain.User;
import com.nhnacademy.jdbc.board.user.service.UserService;
import com.nhnacademy.jdbc.board.user.service.impl.DefaultUserService;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@Slf4j
class PostInsertControllerTest {
    MockMvc mockMvc;
    PostService postService;
    UserService userService;
    MockHttpSession httpSession;
    User user;
    @BeforeEach
    void setUp() {
        postService = mock(DefaultPostService.class);
        userService = mock(DefaultUserService.class);
        httpSession = new MockHttpSession();
        PostInsertController postInsertController = new PostInsertController(postService,userService);
        user  = new User(1L, "a", "a", null);
        mockMvc = MockMvcBuilders.standaloneSetup(postInsertController).build();
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
        MvcResult result = mockMvc.perform(post("/insert")
                        .param("title", "title")
                        .param("content", "content")
                        .session(httpSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/showPosts"))
                .andReturn();

        assertThat(result.getResponse().getRedirectedUrl()).isEqualTo("/showPosts");
        verify(postService, times(1)).writePost(any());
    }

    @Test
    void insertGetTest() throws Exception {
        mockMvc.perform(get("/insert"))
                .andExpect(status().isOk())
                .andExpect(view().name("showInsertForm"));

    }
}