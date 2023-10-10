package com.ficrew.yourbutler.realestates.presentation.response;

import com.ficrew.yourbutler.realestates.domain.entity.RealestateDocument;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RealestateDetailResponse {

    private final Long realestateId;
    private final BookmarkCheckResponse bookmark;
    private final Long complexNo;
    private final String complexName;
    private final String roomType;
    private final String realEstateTypeName;
    private final String sidoName;
    private final String guName;
    private final String dongName;
    private final String address;
    private final Double latitude;
    private final Double longitude;
    private final String dealOrWarrantPrc;
    private final Long dealOrWarrantPrc_numeric;
    private final String rentPrc;
    private final Long rentPrcNumeric;
    private final Long maintenanceFee;
    private final Object imageList;
    private final String tagList;
    private final String floorInfo;
    private final String buildingName;
    private final Double supplyArea;
    private final Double exclusiveArea;
    private final Integer exclusiveRate;
    private final Integer roomCnt;
    private final String bathroomCnt;
    private final String direction;
    private final String description;
    private final String lawUsage;
    private final String approvalDate;
    private final Long articleConfirmYmd;
    private final String realtorName;
    private final String realtorcellPhoneNo;
    private final String realtorAddress;
    private final Object realTransactionPriceList;
    public static RealestateDetailResponse from(RealestateDocument realestateDocument, BookmarkCheckResponse bookmarkResponse) {
        System.out.println(realestateDocument);
        return new RealestateDetailResponse(
            realestateDocument.getArticleNo(),
            bookmarkResponse,
            realestateDocument.getComplexNo(),
            realestateDocument.getComplexName(),
            realestateDocument.getRoomType(),
            realestateDocument.getRealEstateTypeName(),
            realestateDocument.getSidoName(),
            realestateDocument.getGuName(),
            realestateDocument.getDongName(),
            realestateDocument.getAddress(),
            realestateDocument.getLatitude(),
            realestateDocument.getLongitude(),
            realestateDocument.getDealOrWarrantPrc(),
            realestateDocument.getDealOrWarrantPrc_numeric(),
            realestateDocument.getRentPrc(),
            realestateDocument.getRentPrc_numeric(),
            realestateDocument.getMaintenanceFee(),
            realestateDocument.getFormatted_image_list(),
            realestateDocument.getTagList(),
            realestateDocument.getFloorInfo(),
            realestateDocument.getBuildingName(),
            realestateDocument.getSupplyArea(),
            realestateDocument.getExclusiveArea(),
            realestateDocument.getExclusiveRate(),
            realestateDocument.getRoomCnt(),
            realestateDocument.getBathroomCnt(),
            realestateDocument.getDirection(),
            realestateDocument.getDescription(),
            realestateDocument.getLawUsage(),
            realestateDocument.getApprovalDate(),
            realestateDocument.getArticleConfirmYmd(),
            realestateDocument.getRealtorName(),
            realestateDocument.getRealtorcellPhoneNo(),
            realestateDocument.getRealtorAddress(),
            realestateDocument.getRealTransactionPriceList()
        );
    }

}
