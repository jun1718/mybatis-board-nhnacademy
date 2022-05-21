package com.nhnacademy.jdbc.board.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class LooutControllerTest {
    MockMvc mockMvc;
    MockHttpSession session;
    @BeforeEach
    void setUp() {
        session = new MockHttpSession();
        mockMvc = MockMvcBuilders.standaloneSetup(new LogoutController()).build();
    }

    @Test
    void logoutNoSessionTest() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"));
    }

    @Test
    void logoutSuccessTest() throws Exception {
        mockMvc.perform(get("/logout")
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"));
    }
}