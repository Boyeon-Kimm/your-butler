package com.ficrew.yourbutler.global.config;

import com.ficrew.yourbutler.global.auth.AuthenticationService;
import com.ficrew.yourbutler.global.auth.filter.AuthEntryPoint;
import com.ficrew.yourbutler.global.auth.filter.AuthTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationService authenticationService;

    private final AuthEntryPoint unauthorizedHandler;

    private final AuthTokenFilter authenticationJwtFilter;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
        throws Exception {
        authenticationManagerBuilder.userDetailsService(authenticationService)
            .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests()
            .antMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
            .antMatchers("/api/v1/**").permitAll()
            .antMatchers("/h2-console/**").permitAll()
            .antMatchers("/api/v1/realestates/**/check").authenticated()
            .antMatchers("/api/v1/reakestates/**/uncheck").authenticated()
            .antMatchers("/api/v1/realestates/bookmarks").authenticated()
            .anyRequest().permitAll().and()
            .headers().frameOptions().disable();
        http.addFilterBefore(authenticationJwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    // REST API CORS 설정
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(
            Collections.singletonList("*")); // "*" 대신 허용할 도메인 패턴을 지정합니다.
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}