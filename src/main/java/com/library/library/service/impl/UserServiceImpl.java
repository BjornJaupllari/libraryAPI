package com.library.library.service.impl;

import com.library.library.dto.User.CreateUserDto;
import com.library.library.dto.User.UpdateUserDto;
import com.library.library.dto.User.UserDto;
import com.library.library.entity.User;
import com.library.library.exeption.GlobalException;
import com.library.library.mapper.UserMapper;
import com.library.library.repository.UserRepository;
import com.library.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CreateUserDto createUser(CreateUserDto userDTO) {
        Optional<User> existingUser = userRepository.findByEmail(userDTO.getEmail());

        if (existingUser.isPresent()) {
            throw new GlobalException("User with email " + userDTO.getEmail() + " already exists");
        }

        User userEntity = userMapper.createDtoToEntity(userDTO);
        userEntity.setCreatedAt(new Date());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        User savedUser = userRepository.save(userEntity);
        return userMapper.entityToCreateDto(savedUser);
    }

    @Override
    public UserDto getUserById(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GlobalException("User with ID " + userId + " not found"));

        return userMapper.entityToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();


        return users.stream()
                .map(userMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateUser(int userId, UpdateUserDto userDTO) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new GlobalException("User with ID " + userId + " not found"));

        System.out.println("User before update: " + existingUser);

        userMapper.updateUserFromDto(userDTO, existingUser);
        existingUser.setUpdatedAt(new Date());

        userRepository.save(existingUser);
    }

    @Override
    public void softDeleteUser(int userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new GlobalException("User with ID " + userId + " not found"));

        existingUser.setDeletedAt(new Date());
        userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new GlobalException("User with ID " + userId + " not found"));

        userRepository.deleteById(userId);
    }
}
