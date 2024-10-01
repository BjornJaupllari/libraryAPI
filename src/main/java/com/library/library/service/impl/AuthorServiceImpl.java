package com.library.library.service.impl;

import com.library.library.dto.Author.AuthorDto;
import com.library.library.dto.Author.CreateAuthorDto;
import com.library.library.entity.Author;
import com.library.library.exeption.GlobalException;
import com.library.library.mapper.AuthorMapper;
import com.library.library.repository.AuthorRepository;
import com.library.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public AuthorDto createAuthor(CreateAuthorDto createAuthorDto) {
        Author author = authorMapper.dtoToEntity(createAuthorDto);

        author.setCreatedAt(new Date());
        Author savedAuthor = authorRepository.save(author);

        return authorMapper.entityToDto(savedAuthor);
    }

    @Override
    public AuthorDto getAuthorById(int authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new GlobalException("Author with ID " + authorId + " not found"));

        return authorMapper.entityToDto(author);
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        List<Author> author = authorRepository.findAll();

        return author.stream()
                .map(authorMapper::entityToDto)
                .collect(Collectors.toList());
    }
}
