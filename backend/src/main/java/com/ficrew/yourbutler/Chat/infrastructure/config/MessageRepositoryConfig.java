package com.ficrew.yourbutler.chat.infrastructure.config;

import com.ficrew.yourbutler.chat.domain.repository.MessageRepository;
import com.ficrew.yourbutler.chat.infrastructure.persistence.MessageRepositoryAdapter;
import com.ficrew.yourbutler.chat.infrastructure.persistence.jpa.JpaMessageRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageRepositoryConfig {

    @Bean
    public MessageRepository messageRepository(JpaMessageRepository jpaMessageRepository) {
        return new MessageRepositoryAdapter(jpaMessageRepository);
    }

}
