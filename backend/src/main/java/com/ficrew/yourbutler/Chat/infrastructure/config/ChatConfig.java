package com.ficrew.yourbutler.Chat.infrastructure.config;

import com.ficrew.yourbutler.Chat.application.CreateMessageProcessor;
import com.ficrew.yourbutler.Chat.application.GetAllChatListProcessor;
import com.ficrew.yourbutler.Chat.application.GetMessageListProcessor;
import com.ficrew.yourbutler.Chat.domain.repository.BankRepository;
import com.ficrew.yourbutler.Chat.domain.repository.ChatRepository;
import com.ficrew.yourbutler.Chat.domain.repository.MessageRepository;
import com.ficrew.yourbutler.member.domain.repository.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ChatConfig {

    @Bean
    public CreateMessageProcessor createMessageProcessor(
            ChatRepository chatRepository, MessageRepository messageRepository, MemberRepository memberRepository, BankRepository bankRepository, WebClient webClient
    ) {
        return new CreateMessageProcessor(
                chatRepository, messageRepository, memberRepository, bankRepository, webClient
        );
    }

    @Bean
    public GetMessageListProcessor getMessageListProcessor(
            ChatRepository chatRepository, MessageRepository messageRepository, MemberRepository memberRepository
    ) {
        return new GetMessageListProcessor(
                chatRepository, messageRepository, memberRepository
        );
    }

    @Bean
    public GetAllChatListProcessor getAllChatListProcessor(
            ChatRepository chatRepository, MessageRepository messageRepository, MemberRepository memberRepository
    ) {
        return new GetAllChatListProcessor(
                chatRepository, messageRepository, memberRepository
        );
    }

}
