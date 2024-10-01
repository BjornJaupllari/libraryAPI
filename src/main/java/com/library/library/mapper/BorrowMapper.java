package com.library.library.mapper;

import com.library.library.dto.Borrow.BorrowDto;
import com.library.library.entity.Borrow;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface BorrowMapper {

    BorrowDto entityToDto(Borrow borrow);

//    Borrow dtoToEntity(CreateBorrowDto createBorrowDto);

}
