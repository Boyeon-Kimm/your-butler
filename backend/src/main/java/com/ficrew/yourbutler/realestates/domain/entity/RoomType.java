package com.ficrew.yourbutler.realestates.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoomType {
    // (APT/OPST/DDDGG/OTROOM/VL/JT)
    APT("아파트"),
    OPST("오피스텔"),
    DDDGG("단독/다가구"),
    OTROOM("방"),
    VL("빌라/연립"),
    JT("상가주택"); // 상가주택, 한옥주택, 전원주택

    private final String description;
}
