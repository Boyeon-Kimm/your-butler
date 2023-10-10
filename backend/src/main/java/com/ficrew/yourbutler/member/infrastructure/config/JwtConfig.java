package com.ficrew.yourbutler.member.infrastructure.config;

import com.ficrew.yourbutler.member.infrastructure.jwt.JwtMaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.authentication.AuthenticationManagerFactoryBean;

@Configuration
public class JwtConfig {
    @Bean
    public JwtMaker jwtMaker(
        AuthenticationManager authenticationManager
    ) {
        return new JwtMaker(
            authenticationManager
        );
    }

    @Bean
    public AuthenticationManagerFactoryBean authenticationManager() {
        return new AuthenticationManagerFactoryBean();
    }
}
