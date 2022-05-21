package com.nhnacademy.jdbc.board.web;

import com.nhnacademy.jdbc.board.post.domain.PostVoAboutDetailUp;
import com.nhnacademy.jdbc.board.post.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PostModifyControllerTest {
    PostService postService;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        postService =mock(PostService.class);
        PostModifyController postModifyController = new PostModifyController(postService);
        mockMvc = MockMvcBuilders.standaloneSetup(postModifyController).build();
    }

    @Test
    void postModifyControllerGetTest() throws Exception {
        PostVoAboutDetailUp postVoAboutDetailUp =
                new PostVoAboutDetailUp(1L, "", "", "", "", "", "", null,null);
        when(postService.getPostUp(any())).thenReturn(Optional.of(postVoAboutDetailUp));

        mockMvc.perform(get("/modify")
                .param("postNo", String.valueOf(1L)))
                .andExpect(status().isOk())
                .andExpect(model().attribute("postUp",postVoAboutDetailUp));

        verify(postService, times(1)).getPostUp(any());
    }

    @Test
    void doModifyTest() throws Exception {

        when(postService.modifyPost(any(), any(), any())).thenReturn(1L);
        mockMvc.perform(post("/modify")
                .param("postNo",String.valueOf(1L))
                .param("postTitle","test")
                .param("postContent","test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/showPosts"));

        verify(postService, times(1)).modifyPost(any(), any(), any());

    }
}