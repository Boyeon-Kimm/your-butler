package com.ficrew.yourbutler.global.auth.config;

import com.ficrew.yourbutler.global.auth.AuthenticationService;
import com.ficrew.yourbutler.global.auth.JWTProvider;
import com.ficrew.yourbutler.global.auth.filter.AuthTokenFilter;
import com.ficrew.yourbutler.member.domain.repository.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig {
    @Bean
    public AuthenticationService authenticationService(
            MemberRepository memberRepository
    ) {
        return new AuthenticationService(memberRepository);
    }

    @Bean
    public AuthTokenFilter authenticationJwtFilter(
            JWTProvider jwtProvider,
            AuthenticationService authenticationService
    ) {
        return new AuthTokenFilter(jwtProvider, authenticationService);
    }
}
