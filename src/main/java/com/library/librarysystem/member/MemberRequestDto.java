
package com.library.librarysystem.member;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class MemberRequestDto {

    @Length(message = "Name must be at least 2 characters", min = 2, max = 100)
    @NotBlank(message = "Member name must not be blank")
    private String name;

    @NotBlank(message = "Member email must not be blank")
    private String email;
}