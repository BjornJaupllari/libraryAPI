package com.library.library.service;

import com.library.library.dto.User.CreateUserDto;
import com.library.library.dto.User.UpdateUserDto;
import com.library.library.dto.User.UserDto;
import com.library.library.entity.User;
import com.library.library.mapper.UserMapper;
import com.library.library.repository.UserRepository;
import com.library.library.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private CreateUserDto createUserDto;
    private UserDto userDto;
    private UpdateUserDto updateUserDto;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        createUserDto = new CreateUserDto();
        createUserDto.setName("Bujar");
        createUserDto.setEmail("Bujar@example.com");
        createUserDto.setPassword("password123");
        createUserDto.setPhoneNumber("+355");
        createUserDto.setRole("ADMIN");

        userDto = new UserDto();
        userDto.setId(1);
        userDto.setName("Bujar");
        userDto.setEmail("Bujar@example.com");

        user = new User();
        user.setId(1);
        user.setName("Bujar");
        user.setEmail("Bujar@example.com");

        updateUserDto = new UpdateUserDto();
        updateUserDto.setName("Bujar Updated");
    }

    @Test
    void testCreateUser() {
        when(passwordEncoder.encode(createUserDto.getPassword())).thenReturn("encodedPassword123");
        when(userMapper.createDtoToEntity(createUserDto)).thenReturn(user);

        when(userRepository.save(user)).thenReturn(user);

        when(userMapper.entityToCreateDto(user)).thenReturn(createUserDto);

        CreateUserDto result = userService.createUser(createUserDto);

        assertNotNull(result, "The result should not be null");
        assertEquals(createUserDto.getName(), result.getName(), "User name should match");

        verify(userRepository, times(1)).save(user);
        verify(passwordEncoder, times(1)).encode(createUserDto.getPassword());
    }


    @Test
    void testGetUserById() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userMapper.entityToDto(user)).thenReturn(userDto);

        UserDto result = userService.getUserById(1);
        assertNotNull(result);
        assertEquals(userDto.getName(), result.getName());

        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void testGetAllUsers() {
        List<User> users = Arrays.asList(user);
        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.entityToDto(user)).thenReturn(userDto);

        List<UserDto> result = userService.getAllUsers();
        assertNotNull(result);
        assertEquals(1, result.size());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testUpdateUser() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        doNothing().when(userMapper).updateUserFromDto(updateUserDto, user);
        when(userRepository.save(user)).thenReturn(user);

        userService.updateUser(1, updateUserDto);

        verify(userRepository, times(1)).findById(1);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testDeleteUser() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        doNothing().when(userRepository).deleteById(1);

        userService.deleteUser(1);

        verify(userRepository, times(1)).findById(1);
        verify(userRepository, times(1)).deleteById(1);
    }


    @Test
    void testSoftDeleteUser() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        user.setDeletedAt(new Date());

        userService.softDeleteUser(1);

        verify(userRepository, times(1)).save(user);
    }
}
