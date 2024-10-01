package com.library.library.dto.Borrow;

import com.library.library.dto.Book.BookDto;
import com.library.library.dto.User.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BorrowDto {

    private int id;

    private UserDto user;

    private BookDto book;

    private Date borrowDate;

    private Date broughtDate;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

}
