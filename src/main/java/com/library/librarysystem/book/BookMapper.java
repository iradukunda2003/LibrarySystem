package com.library.librarysystem.book;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface BookMapper {


    Book mapToEntity(BookDto bookDto);


    Book updateEntity(BookDto bookDto, @MappingTarget Book book);
}
