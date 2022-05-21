package com.nhnacademy.jdbc.board.web;

import com.nhnacademy.jdbc.board.comment.domain.Comment;
import com.nhnacademy.jdbc.board.comment.service.CommentService;
import com.nhnacademy.jdbc.board.user.domain.User;
import com.nhnacademy.jdbc.board.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CommentControllerTest {
    MockMvc mockMvc;
    CommentService commentService;
    UserService userService;

    @BeforeEach
    void setUp() {
        commentService = mock(CommentService.class);
        userService = mock(UserService.class);
        CommentController commentController = new CommentController(commentService, userService);
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
    }

    @Test
    void getComment() throws Exception {
        User user = new User(1L, "a", "a", "a");

        when(userService.getUserById(any())).thenReturn(Optional.of(user));

        mockMvc.perform(get("/comment")
                        .param("postNo", String.valueOf(1L))
                        .param("userId", "test"))
                .andExpect(status().isOk())
                .andExpect(view().name("commentInsertForm"))
                .andExpect(model().attribute("postNo", 1L))
                .andExpect(model().attribute("userNo", 1L));

        verify(userService, times(1)).getUserById(any());
    }

    @Test
    void doPostFailTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/comment")
                        .param("content", "")
                        .param("postNo", String.valueOf(1L))
                        .param("userNo", String.valueOf(1L)))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/comment"))
                .andReturn();

        assertThat(mvcResult.getResponse().getRedirectedUrl()).isEqualTo("/comment");
    }

    @Test
    void doPostSuccessTest() throws Exception {
        when(commentService.insertComment(any())).thenReturn(1L);
        MvcResult mvcResult = mockMvc.perform(post("/comment")
                        .param("content", "test")
                        .param("postNo", String.valueOf(1L))
                        .param("userNo", String.valueOf(1L)))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/showPost?postNo="+1L))
                .andReturn();

        assertThat(mvcResult.getResponse().getRedirectedUrl()).isEqualTo("/showPost?postNo=1");
        verify(commentService, times(1)).insertComment(any());
    }
}