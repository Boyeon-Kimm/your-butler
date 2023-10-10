package com.ficrew.yourbutler.member.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateMemberCommand {

    private String identifier;
    private String socialType;
    private String email;
    private String nickname;
    private Integer age;
    private Long holdingAsset;
    private Integer creditRating;
    private Long monthlyAvailableAsset;
    private String numberOfHouses;

}
