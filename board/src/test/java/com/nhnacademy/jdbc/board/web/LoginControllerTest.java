package com.nhnacademy.jdbc.board.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.mysql.cj.Session;
import com.nhnacademy.jdbc.board.user.domain.User;
import com.nhnacademy.jdbc.board.user.service.UserService;
import com.nhnacademy.jdbc.board.user.service.impl.DefaultUserService;
import java.net.http.HttpRequest;
import java.util.Objects;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        session.setAttribute("id","admin");
        LoginController loginController = new LoginController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    @Test
    void getLoginWithOutSession() throws Exception {
        mockMvc.perform(get("/login"))
            .andExpect(status().isOk())
            .andExpect(view().name("loginForm"));
    }

    @Test
    void getLoginWithSession() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/login")
                .session(session))
            .andExpect(status().isOk())
            .andExpect(view().name("loginSuccess"))
            .andReturn();
        String id = (String) mvcResult.getModelAndView().getModel().get("id");
        assertThat(id).isEqualTo("admin");
    }

    @Test
    void postDoLoginSuccessfulTest() throws Exception {
        User user = new User(1L, "admin", "admin", "admin");
        when(Objects.isNull(service.checkUser(anyString(), anyString()).orElse(null))).thenReturn(
            false);

        MvcResult mvcResult = mockMvc.perform(post("/login")
                .param("id", "admin")
                .param("id", "admin"))
            .andExpect(status().isOk())
            .andExpect(view().name("loginSuccess"))
            .andReturn();
    }
}