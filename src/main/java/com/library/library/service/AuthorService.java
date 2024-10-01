package com.library.library.service;

import com.library.library.dto.Author.AuthorDto;
import com.library.library.dto.Author.CreateAuthorDto;

import java.util.List;

public interface AuthorService {

    AuthorDto createAuthor(CreateAuthorDto createAuthorDto);

    AuthorDto getAuthorById(int authorId);

    List<AuthorDto> getAllAuthors();

}
