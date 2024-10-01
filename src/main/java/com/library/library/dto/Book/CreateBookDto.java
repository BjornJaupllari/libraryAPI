package com.library.library.dto.Book;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CreateBookDto {

    @NotBlank(message = "Title of the book is mandatory!")
    private String title;

    private String genre;

    private float price;

    private Date publishDate;
}
