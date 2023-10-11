package com.ficrew.yourbutler.chat.application.result;

import com.ficrew.yourbutler.chat.presentation.response.ChatResponse;
import com.ficrew.yourbutler.chat.presentation.response.MessageResponse;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ChatResult {

    private final String buildingName;
    private final List<MessageResult> messageList;

    public ChatResult(String buildingName, List<MessageResult> messageList) {
        this.buildingName = buildingName;
        this.messageList = messageList;
    }

    public ChatResponse toResponse() {
        List<MessageResponse> messageResponses = this.messageList.stream()
                .map(MessageResponse::new)
                .collect(Collectors.toList());

        return new ChatResponse(this.buildingName, messageResponses);
    }

}
