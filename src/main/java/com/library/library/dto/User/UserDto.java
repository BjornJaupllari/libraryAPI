package com.library.library.dto.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDto {

    private int id;

    private String name;

    private String email;

    private String phoneNumber;

    private int age;

    private String gender;

    private String role;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

}
