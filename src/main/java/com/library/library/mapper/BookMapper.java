package com.library.library.mapper;

import com.library.library.dto.Book.BookDto;
import com.library.library.dto.Book.CreateBookDto;
import com.library.library.dto.User.CreateUserDto;
import com.library.library.entity.Book;
import com.library.library.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface BookMapper {

    BookDto entityToDto(Book book);

    Book dtoToEntity(CreateBookDto createBookDto);
}
