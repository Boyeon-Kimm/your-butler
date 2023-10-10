package com.ficrew.yourbutler.member.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EditMemberCommand {

    private Integer age;
    private String numberOfHouses;
    private Long holdingAsset;
    private Long monthlyAvailableAsset;
    private Integer creditRating;


}
