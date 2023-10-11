package com.ficrew.yourbutler.chat.infrastructure.config;

import com.ficrew.yourbutler.chat.application.CreateMessageProcessor;
import com.ficrew.yourbutler.chat.application.GetAllChatListProcessor;
import com.ficrew.yourbutler.chat.application.GetMessageListProcessor;
import com.ficrew.yourbutler.chat.domain.repository.BankRepository;
import com.ficrew.yourbutler.chat.domain.repository.ChatRepository;
import com.ficrew.yourbutler.chat.domain.repository.MessageRepository;
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
