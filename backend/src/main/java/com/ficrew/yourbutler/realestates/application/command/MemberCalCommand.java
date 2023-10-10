package com.ficrew.yourbutler.realestates.application.command;

import com.ficrew.yourbutler.member.domain.entity.NumberOfHouses;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberCalCommand {
    private Integer creditRating;
    private NumberOfHouses numberOfHouses;

}
