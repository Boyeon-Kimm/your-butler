package com.ficrew.yourbutler.Chat.infrastructure.config;

import com.ficrew.yourbutler.Chat.domain.repository.BankRepository;
import com.ficrew.yourbutler.Chat.infrastructure.persistence.BankRepositoryAdapter;
import com.ficrew.yourbutler.Chat.infrastructure.persistence.jpa.JpaBankRepository;
import org.springframework.context.annotation.Bean;

public class BankRepositoryConfig {

    @Bean
    public BankRepository bankRepository(JpaBankRepository jpaBankRepository) {
        return new BankRepositoryAdapter(jpaBankRepository);
    }

}
