package com.ficrew.yourbutler.chat.infrastructure.config;

import com.ficrew.yourbutler.chat.domain.repository.ChatRepository;
import com.ficrew.yourbutler.chat.infrastructure.persistence.ChatRepositoryAdapter;
import com.ficrew.yourbutler.chat.infrastructure.persistence.jpa.JpaChatRoomRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatRepositoryConfig {

    @Bean
    public ChatRepository chatRepository(JpaChatRoomRepository jpaChatRoomRepository) {
        return new ChatRepositoryAdapter(jpaChatRoomRepository);
    }

}
