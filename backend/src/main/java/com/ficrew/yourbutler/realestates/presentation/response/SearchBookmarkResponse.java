package com.ficrew.yourbutler.realestates.presentation.response;

import com.ficrew.yourbutler.realestates.domain.entity.RealestateDocument;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SearchBookmarkResponse {

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
    private final String color;
    private final Object imageSrc;
    private final BookmarkCheckResponse bookmark;

    public static SearchBookmarkResponse from(
        RealestateDocument realestateDocument,
        String color,
        BookmarkCheckResponse bookmarkCheckResponse
    ) {
        return new SearchBookmarkResponse(
            realestateDocument.getArticleNo(),
            realestateDocument.getRoomType(),
            realestateDocument.getComplexName(),
            realestateDocument.getRealEstateTypeName(),
            realestateDocument.getSidoName(),
            realestateDocument.getGuName(),
            realestateDocument.getDongName(),
            realestateDocument.getAddress(),
            realestateDocument.getLatitude(),
            realestateDocument.getLongitude(),
            realestateDocument.getTagList(),
            realestateDocument.getDealOrWarrantPrc(),
            realestateDocument.getRentPrc(),
            realestateDocument.getFloorInfo(),
            (int) (realestateDocument.getExclusiveArea() * 0.035),
            realestateDocument.getSupplyArea(),
            realestateDocument.getExclusiveArea(),
            realestateDocument.getMaintenanceFee(),
            color,
            realestateDocument.getFormatted_image_list(),
            bookmarkCheckResponse
        );
    }

}