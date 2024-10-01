package com.library.library.repository;


import com.library.library.entity.Book;
import com.library.library.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Integer> {

    @Query("SELECT b.book FROM Borrow b WHERE b.user.id = :userId")
    List<Book> findBooksBorrowedByUserId(int userId);

    @Query("SELECT COUNT(b) > 0 FROM Borrow b WHERE b.book.id = :bookId AND b.broughtDate IS NULL")
    boolean isBookCurrentlyBorrowed(@Param("bookId") int bookId);

}
