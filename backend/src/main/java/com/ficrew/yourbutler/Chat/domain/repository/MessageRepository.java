package com.ficrew.yourbutler.chat.domain.repository;

import com.ficrew.yourbutler.chat.domain.entity.ChatRoom;
import com.ficrew.yourbutler.chat.domain.entity.Message;

import java.util.List;

public interface MessageRepository {

    Message save(Message message);
    List<Message> findAllByChatRoom(ChatRoom chatRoom);

}
