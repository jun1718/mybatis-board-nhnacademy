package com.nhnacademy.jdbc.board.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.jdbc.board.user.domain.User;
import com.nhnacademy.jdbc.board.user.service.UserService;
import com.nhnacademy.jdbc.board.user.service.impl.DefaultUserService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class LoginControllerTest {
    private MockMvc mockMvc;
    private MockHttpSession session;
    private UserService service;

    @BeforeEach
    void setUp() {
        service = mock(DefaultUserService.class);
        session = new MockHttpSession();
        session.setAttribute("id", "admin");
        LoginController loginController = new LoginController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    @Test
    void getLoginWithOutSession() throws Exception {
        MockHttpSession test = new MockHttpSession();
        mockMvc.perform(get("/login", "/"))
            .andExpect(status().isOk())
            .andExpect(view().name("loginForm"));
    }

    @Test
    void getLoginWithSession() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/login")
                .session(session))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/showPosts?page="+null))
            .andExpect(model().attribute("id", session.getAttribute("id")))
            .andReturn();
        String id = (String) mvcResult.getModelAndView().getModel().get("id");
        assertThat(id).isEqualTo("admin");
    }

    @Test
    void postDoLoginSuccessfulTest() throws Exception {
        User user = new User(1L, "admin", "admin", "admin");
        when(service.checkUser(anyString(), anyString())).thenReturn(Optional.of(user));

        mockMvc.perform(post("/login")
                .param("id", "admin")
                .param("pwd", "admin"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/login"))
            .andExpect(model().attribute("id", "admin"))
            .andReturn();

        verify(service, times(1)).checkUser(any(), any());

    }

    @Test
    void postDoLoginFailTest() throws Exception {
        User user = new User(null, null, null, null);
        when(service.checkUser(any(), any())).thenReturn(Optional.empty());
        mockMvc.perform(post("/login")
                .param("id", "admin")
                .param("pwd", "admin"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/login"))
            .andReturn();

        verify(service, times(1)).checkUser(any(), any());
    }
}