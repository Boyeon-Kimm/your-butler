package com.ficrew.yourbutler.chat.infrastructure.config;

import com.ficrew.yourbutler.chat.domain.repository.BankRepository;
import com.ficrew.yourbutler.chat.infrastructure.persistence.BankRepositoryAdapter;
import com.ficrew.yourbutler.chat.infrastructure.persistence.jpa.JpaBankRepository;
import org.springframework.context.annotation.Bean;

public class BankRepositoryConfig {

    @Bean
    public BankRepository bankRepository(JpaBankRepository jpaBankRepository) {
        return new BankRepositoryAdapter(jpaBankRepository);
    }

}
