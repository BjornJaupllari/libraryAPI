package com.library.library.controller;

import com.library.library.dto.User.CreateUserDto;
import com.library.library.dto.User.UpdateUserDto;
import com.library.library.dto.User.UserDto;
import com.library.library.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "ADMIN only.")
    @PostMapping("/create")
    public ResponseEntity<CreateUserDto> createUser(@RequestBody CreateUserDto createUserDto) {
        CreateUserDto createdUser = userService.createUser(createUserDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "ADMIN only.")
    @GetMapping("/get/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable(value = "userId") int userId) {
        UserDto user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "ADMIN only.")
    @GetMapping("/get/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "ADMIN only.")
    @PutMapping("/update/{userId}")
    public ResponseEntity<UpdateUserDto> updateUser(@Valid @PathVariable("userId") int userId,
                                                    @RequestBody UpdateUserDto updateUserDto) {
        userService.updateUser(userId, updateUserDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "ADMIN only.")
    @DeleteMapping("/delete/permanently/{userId}")
    public ResponseEntity deleteUser(@PathVariable("userId") int userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "ADMIN only.")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity softDeleteUser(@PathVariable("userId") int userId) {
        userService.softDeleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
