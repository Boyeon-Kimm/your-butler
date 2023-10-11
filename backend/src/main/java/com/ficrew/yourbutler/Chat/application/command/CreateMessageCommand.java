package com.ficrew.yourbutler.chat.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateMessageCommand {

    private Boolean isBot;
    private Long chatRoomNo;
    private Long realestateId;
    private String buildingName;
    private String sidoName;
    private String guName;
    private String dongName;
    private Long dealOrWarrantPrcNumeric;
    private Long rentPrcNumeric;
    private String chat;
    private Long myMoney;

}
