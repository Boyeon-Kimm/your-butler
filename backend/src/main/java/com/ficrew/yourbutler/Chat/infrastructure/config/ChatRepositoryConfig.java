package com.ficrew.yourbutler.Chat.infrastructure.config;

import com.ficrew.yourbutler.Chat.domain.repository.ChatRepository;
import com.ficrew.yourbutler.Chat.domain.repository.MessageRepository;
import com.ficrew.yourbutler.Chat.infrastructure.persistence.ChatRepositoryAdapter;
import com.ficrew.yourbutler.Chat.infrastructure.persistence.jpa.JpaChatRoomRepository;
import com.ficrew.yourbutler.Chat.infrastructure.persistence.jpa.JpaMessageRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatRepositoryConfig {

    @Bean
    public ChatRepository chatRepository(JpaChatRoomRepository jpaChatRoomRepository) {
        return new ChatRepositoryAdapter(jpaChatRoomRepository);
    }

}
