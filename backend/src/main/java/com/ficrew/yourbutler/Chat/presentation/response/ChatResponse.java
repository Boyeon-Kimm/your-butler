package com.ficrew.yourbutler.Chat.presentation.response;

import lombok.Getter;

import java.util.List;

@Getter
public class ChatResponse {

    private final String buildingName;
    private final List<MessageResponse> messageList;

    public ChatResponse(String buildingName, List<MessageResponse> messageList) {
        this.buildingName = buildingName;
        this.messageList = messageList;
    }

}
