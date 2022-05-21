package com.nhnacademy.jdbc.board.web;

import com.nhnacademy.jdbc.board.post.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class PostDeleteOrAliveControllerTest {
    MockMvc mockMvc;
    PostService postService;

    @BeforeEach
    void setUp() {
        postService = mock(PostService.class);
        PostDeleteOrAliveController postDeleteOrAliveController = new PostDeleteOrAliveController(postService);
        mockMvc = MockMvcBuilders.standaloneSetup(postDeleteOrAliveController).build();
    }

    @Test
    void deletePostTest() throws Exception {
        when(postService.removePostOrViewPost(any())).thenReturn(1L);
        mockMvc.perform(get("/deleteAndAlive")
                .param("postNo",String.valueOf(1L)))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/showPosts"));

        verify(postService,times(1)).removePostOrViewPost(any());

        for (int i = 1; i <= 1000; i++) {
            if (i % 2 == 0) {
                System.out.printf("insert into Posts (post_no, post_no_above, user_no_writer, user_no_modifier, writed_at, modified_at, title, content, view_activate)\n" +
                    "         values(%d,null,1,null,'2022-05-20 13:03:00',null,'관리자 게시물%d' , '관리자 게시물 내용%d',true);%n", i, i, i, i);
            } else {
                System.out.printf("insert into Posts (post_no, post_no_above, user_no_writer, user_no_modifier, writed_at, modified_at, title, content, view_activate)\n" +
                    "         values(%d,null,2,null,'2022-05-20 13:03:00',null,'유저 게시물%d' , '유저 게시물 내용%d',true);%n", i, i, i, i);
            }

        }
    }
}