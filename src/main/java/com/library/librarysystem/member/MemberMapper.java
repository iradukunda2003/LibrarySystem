package com.library.librarysystem.member;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member mapToEntity(MemberRequestDto memberRequestDto);

    Member updateEntity(MemberRequestDto memberRequestDto, @MappingTarget Member member);

    MemberResponseDto mapToResponseDto(Member member);
}