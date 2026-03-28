package com.library.librarysystem.security;

import com.library.librarysystem.member.Member;
import com.library.librarysystem.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Optional;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    public OAuth2SuccessHandler(MemberRepository memberRepository,
                                JwtUtil jwtUtil) {
        this.memberRepository = memberRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException {


        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String email = oAuth2User.getAttribute("email");
        String name  = oAuth2User.getAttribute("name");


        Optional<Member> existingMember = memberRepository
                .findMemberByEmail(email);

        if (existingMember.isEmpty()) {

            Member newMember = new Member();
            newMember.setName(name);
            newMember.setEmail(email);

            newMember.setPassword("GOOGLE_AUTH");
            newMember.setRole("ROLE_USER");
            memberRepository.save(newMember);
        }


        String token = jwtUtil.generateToken(email);


        response.setContentType("application/json");
        response.getWriter().write(
                "{\"token\": \"" + token + "\", " +
                        "\"message\": \"Google login successful\"}"
        );
    }
}