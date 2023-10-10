package com.ficrew.yourbutler.Chat.presentation.request;

import com.ficrew.yourbutler.Chat.application.command.CreateMessageCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMessageRequest {

    @Min(value = -2, message = "채팅방 번호가 필요합니다.")
    private Long chatRoomNo;

    @Min(0)
    private Long realestateId;

    @NotBlank
    private String buildingName;

    @NotBlank
    private String sidoName;

    @NotBlank
    private String guName;

    @NotBlank
    private String dongName;

    @Min(0)
    private Long dealOrWarrantPrcNumeric;

    @Min(0)
    private Long rentPrcNumeric;

    @NotBlank(message = "채팅 내용이 필요합니다.")
    private String chat;

    private Long myMoney;

    public CreateMessageCommand toCommand() {
        return new CreateMessageCommand(false, chatRoomNo, realestateId, buildingName, sidoName, guName, dongName, dealOrWarrantPrcNumeric, rentPrcNumeric, chat, myMoney);
    }

}
