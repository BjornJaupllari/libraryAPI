package com.library.library.dto.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CreateUserDto {

    @NotBlank(message = "User is mandatory!")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z ]*$", message = "User name should only contain alphabetic characters.")
    private String name;

    @NotBlank(message = "Email is mandatory!")
    @Email
    private String email;

    @NotBlank(message = "Phone Number is mandatory!")
    private String phoneNumber;

    @NotBlank(message = "Password is mandatory!")
    private String password;

    private int age;

    private String gender;

    @NotBlank(message = "Role is mandatory!")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z ]*$", message = "Role should only contain alphabetic characters.")
    private String role;
}
