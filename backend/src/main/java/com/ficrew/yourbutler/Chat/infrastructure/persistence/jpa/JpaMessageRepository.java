package com.ficrew.yourbutler.Chat.infrastructure.persistence.jpa;

import com.ficrew.yourbutler.Chat.domain.entity.ChatRoom;
import com.ficrew.yourbutler.Chat.domain.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaMessageRepository extends JpaRepository<Message, Long> {

    // 해당하는 채팅방에 포함된 모든 메세지를 반환함
    List<Message> findAllByChatRoom(ChatRoom chatRoom);



}
