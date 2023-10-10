package com.ficrew.yourbutler.Chat.infrastructure.config;

import com.ficrew.yourbutler.Chat.domain.repository.MessageRepository;
import com.ficrew.yourbutler.Chat.infrastructure.persistence.MessageRepositoryAdapter;
import com.ficrew.yourbutler.Chat.infrastructure.persistence.jpa.JpaMessageRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageRepositoryConfig {

    @Bean
    public MessageRepository messageRepository(JpaMessageRepository jpaMessageRepository) {
        return new MessageRepositoryAdapter(jpaMessageRepository);
    }

}
