package com.ficrew.yourbutler.Chat.application.result;

import com.ficrew.yourbutler.Chat.domain.entity.Message;
import com.ficrew.yourbutler.Chat.presentation.response.MessageResponse;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MessageResult {

    private final Boolean isBot;
    private final LocalDateTime timeStamp;
    private final String message;
    private final Long chatRoomNumber;
    private String loanName;
    private Double loanInterest;
    private String bankName;
    private String bankTel;
    private String bankSite;

    public MessageResult(Message message) {
        // dto 미리 변환
        this.isBot = message.getIsBot();
        this.timeStamp = message.getTimeStamp();
        this.message = message.getMessage();
        this.chatRoomNumber = message.getChatRoom().getId();

        if (message.getLoan() != null) {
            this.loanName = message.getLoan().getLoanName();
            this.loanInterest = message.getLoan().getLoanInterest();
        }

        if (message.getBank() != null) {
            this.bankName = message.getBank().getBankName();
            this.bankTel = message.getBank().getBankTel();
            this.bankSite = message.getBank().getBankSite();
        }
    }

    public MessageResponse toResponse() {
        return new MessageResponse(this);
    }

}
