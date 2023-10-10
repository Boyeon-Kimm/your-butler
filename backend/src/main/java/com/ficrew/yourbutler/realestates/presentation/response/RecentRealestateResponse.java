package com.ficrew.yourbutler.realestates.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecentRealestateResponse {

    private final Long realestateId;
    private final String roomType;
    private final String complexName;
    private final String realEstateTypeName;
    private final String sidoName;
    private final String guName;
    private final String dongName;
    private final String address;
    private final Double latitude;
    private final Double longitude;
    private final String descriptionTagList;
    private final String dealOrWarrantPrc;
    private final String rentPrc;
    private final String floorInfo;
    private final Integer pyeong;
    private final Double supplyArea;
    private final Double exclusiveArea;
    private final Long maintenanceFee;
    private final Object imageSrc;
}
