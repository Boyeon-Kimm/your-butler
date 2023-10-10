package com.ficrew.yourbutler.member.infrastructure.config;

import com.ficrew.yourbutler.member.domain.repository.MemberRepository;
import com.ficrew.yourbutler.member.infrastructure.persistence.MemberRepositoryAdapter;
import com.ficrew.yourbutler.member.infrastructure.persistence.jpa.JpaMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberRepositoryConfig {

    @Bean
    public MemberRepository memberRepository(JpaMemberRepository jpaMemberRepository) {
        return new MemberRepositoryAdapter(jpaMemberRepository);
    }

}
