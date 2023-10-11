package com.ficrew.yourbutler.chat.presentation.response;

import com.ficrew.yourbutler.chat.application.result.MessageResultList;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MessageResponseList {

    private final long chatRoomNumber;
    private final List<MessageResponse> messageList;

    public MessageResponseList(MessageResultList messageResultList) {
        this.chatRoomNumber = messageResultList.getChatRoomNumber();
        this.messageList = messageResultList.getMessageList()
                .stream()
                .map(MessageResponse::new)
                .collect(Collectors.toList());
    }

}
