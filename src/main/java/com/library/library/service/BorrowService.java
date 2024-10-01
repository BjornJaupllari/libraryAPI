package com.library.library.service;

import com.library.library.dto.Book.BookDto;
import com.library.library.dto.Borrow.BorrowDto;

import java.util.List;

public interface BorrowService {

    BorrowDto createBorrow(int bookId, int userId);

    BorrowDto getBorrowById(int userId);

    List<BorrowDto> getAllBorrowsHistory();

    List<BookDto> getBooksBorrowedByUserId(int userId);

    void returnBorrowedBook(int borrowId);
}
