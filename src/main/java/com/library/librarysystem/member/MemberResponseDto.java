package com.library.librarysystem.member;

import lombok.Data;

@Data
public class MemberResponseDto {

    private Long id;
    private String name;
    private String email;
}