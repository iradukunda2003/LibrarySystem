package com.library.librarysystem.member;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Entity
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id", nullable = false)
    private Long id;

    @Length(min = 2, max = 100, message = "Name must be at least 2 characters")
    @NotBlank(message = "Member name must not be blank")
    @Column(name = "member_name", nullable = false)
    private String name;

    @NotBlank(message = "Email must not be blank")
    @Column(name = "member_email", unique = true, nullable = false)
    private String email;

    // stored as BCrypt hash — never saved as plain text
    @Column(name = "member_password", nullable = false)
    private String password;

    // every new member gets ROLE_USER by default
    @Column(name = "member_role", nullable = false)
    private String role = "ROLE_USER";
}