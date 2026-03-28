package com.library.librarysystem.borrowing;

import com.library.librarysystem.book.Book;
import com.library.librarysystem.member.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BorrowingMapper {

    @Mapping(target = "memberName", source = "member.name")
    @Mapping(target = "bookTitle", source = "book.title")
    BorrowingResponseDto toResponseDto(BorrowingRecord record);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "borrowDate", ignore = true)
    @Mapping(target = "returnDate", ignore = true)
    @Mapping(target = "isReturned", ignore = true)
    BorrowingRecord toEntity(Member member, Book book);
}