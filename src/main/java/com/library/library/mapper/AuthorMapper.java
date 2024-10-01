package com.library.library.mapper;

import com.library.library.dto.Author.AuthorDto;
import com.library.library.dto.Author.CreateAuthorDto;
import com.library.library.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AuthorMapper {

    AuthorDto entityToDto(Author author);

    Author dtoToEntity(CreateAuthorDto createAuthorDto);
}

