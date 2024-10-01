package com.library.library.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "USER")
    void testAccessDeniedForNonAdminRole() throws Exception {
        mockMvc.perform(post("/api/user/create"))
                .andExpect(status().is5xxServerError());
    }

    @Test
    void testAccessWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/api/user/get/all"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testAccessGrantedForAdminRole() throws Exception {
        mockMvc.perform(get("/api/user/get/all"))
                .andExpect(status().isOk());
    }

    @Test
    void testUnauthorizedPostRequest() throws Exception {
        mockMvc.perform(post("/api/user/create"))
                .andExpect(status().isForbidden());
    }
}
