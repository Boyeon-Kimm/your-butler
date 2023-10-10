package com.ficrew.yourbutler.Chat.infrastructure.persistence;

import com.ficrew.yourbutler.Chat.domain.entity.ChatRoom;
import com.ficrew.yourbutler.Chat.domain.entity.Message;
import com.ficrew.yourbutler.Chat.domain.repository.MessageRepository;
import com.ficrew.yourbutler.Chat.infrastructure.persistence.jpa.JpaMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MessageRepositoryAdapter implements MessageRepository {

    private final JpaMessageRepository messageRepository;

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> findAllByChatRoom(ChatRoom chatRoom) {
        return messageRepository.findAllByChatRoom(chatRoom);
    }

}
