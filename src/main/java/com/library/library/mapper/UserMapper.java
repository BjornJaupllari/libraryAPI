package com.library.library.mapper;

import com.library.library.dto.User.CreateUserDto;
import com.library.library.dto.User.UpdateUserDto;
import com.library.library.dto.User.UserDto;
import com.library.library.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    UserDto entityToDto(User user);

    CreateUserDto entityToCreateDto(User user);

    User createDtoToEntity(CreateUserDto userDTO);

    void updateUserFromDto(UpdateUserDto userDTO, @MappingTarget User existingUser);

}
