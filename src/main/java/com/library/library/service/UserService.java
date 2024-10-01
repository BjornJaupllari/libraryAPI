package com.library.library.service;

import com.library.library.dto.User.CreateUserDto;
import com.library.library.dto.User.UpdateUserDto;
import com.library.library.dto.User.UserDto;

import java.util.List;

public interface UserService {

    CreateUserDto createUser(CreateUserDto userDTO);

    UserDto getUserById(int userId);

    List<UserDto> getAllUsers();

    void updateUser(int userId, UpdateUserDto userDTO);

    void deleteUser(int userId);

    void softDeleteUser(int userId);

}
