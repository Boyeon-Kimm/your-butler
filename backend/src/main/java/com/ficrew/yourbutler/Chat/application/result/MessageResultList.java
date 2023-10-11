package com.ficrew.yourbutler.chat.application.result;

import com.ficrew.yourbutler.chat.presentation.response.MessageResponseList;
import lombok.Getter;

import java.util.List;

@Getter
public class MessageResultList {

    private final Long chatRoomNumber;
    private final List<MessageResult> messageList;

    public MessageResultList(Long chatRoomNumber, List<MessageResult> messageList) {
        this.chatRoomNumber = chatRoomNumber;
        this.messageList = messageList;
    }

    public MessageResponseList toResponse() {
        return new MessageResponseList(this);
    }

}
