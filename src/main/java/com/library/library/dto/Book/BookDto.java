package com.library.library.dto.Book;

import com.library.library.dto.Author.AuthorDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BookDto {

    private int id;

    private String title;

    private String genre;

    private float price;

    private Date publishDate;

    private AuthorDto author;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

}
