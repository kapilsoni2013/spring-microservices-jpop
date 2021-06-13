package com.kapilsony.bookservice.mappers;

import com.kapilsony.bookservice.dto.BookRequest;
import com.kapilsony.bookservice.dto.BookResponse;
import com.kapilsony.bookservice.entities.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class BookMapper {

   // @Mapping(target = "id", source = "bookId")
    @Mapping(target = "id", ignore = true)
    public abstract BookEntity toEntity(BookRequest bookRequest);

    @Mapping(target = "bookId",source = "id")
    public abstract BookResponse toDTO(BookEntity bookEntity);

//    @Mapping(target = "id", source = "bookId")
    @Mapping(target = "id", ignore = true)
    public abstract BookEntity updateBookFromDTO(@MappingTarget BookEntity bookEntity, BookRequest bookRequest);
}
