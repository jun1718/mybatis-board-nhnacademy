package com.nhnacademy.jdbc.board.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.jdbc.board.like.service.LikeViewCountService;
import com.nhnacademy.jdbc.board.like.service.impl.DefaultLikeViewCountService;
import com.nhnacademy.jdbc.board.post.domain.PostVoAboutDetailDown;
import com.nhnacademy.jdbc.board.post.domain.PostVoAboutDetailUp;
import com.nhnacademy.jdbc.board.post.domain.PostVoAboutList;
import com.nhnacademy.jdbc.board.post.service.PostService;
import com.nhnacademy.jdbc.board.post.service.impl.DefaultPostService;
import com.nhnacademy.jdbc.board.user.service.UserService;
import com.nhnacademy.jdbc.board.user.service.impl.DefaultUserService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;

class PostShowControllerTest {
    MockMvc mockMvc;
    PostService service;
    LikeViewCountService likeViewCountService;
    UserService userService;
    MockHttpSession session;


    @BeforeEach
    void setUp() {
        service = mock(DefaultPostService.class);
        likeViewCountService = mock(DefaultLikeViewCountService.class);
        userService = mock(DefaultUserService.class);
        session = new MockHttpSession();
        PostShowController controller = new PostShowController(service, likeViewCountService, userService);
        session.setAttribute("id", "admin");
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getPosts() throws Exception {
        PostVoAboutList list = new PostVoAboutList(1L, "a", "af", "b", null, null,
            null, 0L, 1);

        when(service.getPostAll("admin", 1)).thenReturn(List.of(list));
        mockMvc.perform(get("/showPosts"))
            .andExpect(status().isOk())
            .andExpect(view().name("showPostsForm"))
            .andExpect(model().attributeExists("posts"))
            .andExpect(model().attribute("posts", List.of(list)));

        verify(service, times(1)).getPostAll("admin", 1);
    }

    @Test
    void getPost() throws Exception {
        PostVoAboutDetailUp postVoAboutDetailUp = new PostVoAboutDetailUp(1L,"test","test","ad","ㅁㅇ"
            ,null,"",null,null,null,null);

        PostVoAboutDetailDown postVoAboutDetailDown = new PostVoAboutDetailDown("ad","ㅁㅇ","test");

        when(service.getPostUp(1L)).thenReturn(Optional.of(postVoAboutDetailUp));
        when(service.getPostDown(1L)).thenReturn(List.of(postVoAboutDetailDown));

        mockMvc.perform(get("/showPost")
                .param("id", String.valueOf(1L)))
            .andExpect(view().name("showPostForm"))
            .andExpect(model().attribute("postUp", postVoAboutDetailUp))
            .andExpect(model().attribute("postDown", List.of(postVoAboutDetailDown)));

        verify(service, times(1)).getPostUp(any());
        verify(service, times(1)).getPostDown(any());
    }



}