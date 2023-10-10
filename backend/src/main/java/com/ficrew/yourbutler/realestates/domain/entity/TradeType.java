package com.ficrew.yourbutler.realestates.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TradeType {
    DEAL("매매"),
    LEASE("전세"),
    RENT("월세");

    private final String description;
}