package com.library.library.dto.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateUserDto {

    private String name;

    private String email;

    private String phoneNumber;

    private String password;

    private Integer age;

    private String gender;

    private String role;
}
