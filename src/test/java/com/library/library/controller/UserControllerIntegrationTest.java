package com.library.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.library.dto.User.CreateUserDto;
import com.library.library.dto.User.UserDto;
import com.library.library.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private UserController userController;

//    @Test
//    @WithMockUser(roles = "ADMIN")
//    void testCreateUser() throws Exception {
//        CreateUserDto createUserDto = new CreateUserDto();
//        createUserDto.setName("bujar");
//        createUserDto.setPassword("TESTtest");
//        createUserDto.setEmail("bujar@example.com");
//        createUserDto.setPhoneNumber("+355");
//        createUserDto.setRole("USER");
//        createUserDto.setAge(40);
//
//        when(userService.createUser(any(CreateUserDto.class))).thenReturn(createUserDto);
//
//        mockMvc.perform(post("/api/user/create")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(createUserDto)))
//                .andExpect(status().isCreated());
//    }

//    @Test
//    @WithMockUser(roles = "ADMIN")
//    void testGetUser() throws Exception {
//        UserDto userDto = new UserDto();
//        userDto.setId(1);
//        userDto.setName("bujar");
//
//        when(userService.getUserById(1)).thenReturn(userDto);
//
//        mockMvc.perform(get("/api/user/get/{userId}", 1))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("bujar"));
//    }

//    @Test
//    @WithMockUser(roles = "ADMIN")
//    void testGetAllUsers() throws Exception {
//        UserDto userDto = new UserDto();
//        userDto.setId(1);
//        userDto.setName("bujar");
//
//        when(userService.getAllUsers()).thenReturn(Collections.singletonList(userDto));
//
//        mockMvc.perform(get("/api/user/get/all"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].name").value("bujar"));
//    }

//    @Test
//    @WithMockUser(roles = "ADMIN")
//    void testDeleteUser() throws Exception {
//        doNothing().when(userService).deleteUser(1);
//
//        mockMvc.perform(delete("/api/user/delete/permanently/{userId}", 1))
//                .andExpect(status().isNoContent());
//    }

    @Test
    void testUnauthorizedAccess() throws Exception {
        mockMvc.perform(post("/api/user/create"))
                .andExpect(status().isForbidden());
    }
}
