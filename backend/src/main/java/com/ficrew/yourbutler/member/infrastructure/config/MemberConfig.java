package com.ficrew.yourbutler.member.infrastructure.config;

import com.ficrew.yourbutler.global.auth.JWTProvider;
import com.ficrew.yourbutler.member.application.CreateMemberProcessor;
import com.ficrew.yourbutler.member.application.DeleteMemberProcessor;
import com.ficrew.yourbutler.member.application.EditMemberProcessor;
import com.ficrew.yourbutler.member.application.SignInMemberProcessor;
import com.ficrew.yourbutler.member.domain.repository.MemberRepository;
import com.ficrew.yourbutler.member.infrastructure.encryption.UserSecurityPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class MemberConfig {

    @Bean
    public CreateMemberProcessor createMemberProcessor(
        MemberRepository memberRepository
    ) {
        return new CreateMemberProcessor(
                memberRepository
        );
    }

    @Bean
    public SignInMemberProcessor signInMemberProcessor(
        MemberRepository memberRepository,
        JWTProvider jwtProvider
    ) {
        return new SignInMemberProcessor(
                memberRepository,
                new UserSecurityPasswordEncoder(userEncodingAlgorithm()),
                jwtProvider
        );
    }

    @Bean
    public EditMemberProcessor editMemberProcessor(
        MemberRepository memberRepository
    ) {
        return new EditMemberProcessor(
                memberRepository
        );
    }

    @Bean
    public DeleteMemberProcessor deleteMemberProcessor(
        MemberRepository memberRepository
    ) {
        return new DeleteMemberProcessor(
                memberRepository
        );
    }

    @Bean
    public PasswordEncoder userEncodingAlgorithm() {
        return new BCryptPasswordEncoder();
    }

}
