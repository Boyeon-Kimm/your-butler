package com.ficrew.yourbutler.Chat.domain.repository;

import com.ficrew.yourbutler.Chat.domain.entity.ChatRoom;
import com.ficrew.yourbutler.Chat.domain.entity.Message;

import java.util.List;

public interface MessageRepository {

    Message save(Message message);
    List<Message> findAllByChatRoom(ChatRoom chatRoom);

}
