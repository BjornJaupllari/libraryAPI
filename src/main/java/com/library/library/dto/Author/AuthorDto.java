package com.library.library.dto.Author;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AuthorDto {

    private int id;

    private String name;

    private String bio;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

}
