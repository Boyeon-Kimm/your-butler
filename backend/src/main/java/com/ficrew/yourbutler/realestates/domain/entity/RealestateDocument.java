package com.ficrew.yourbutler.realestates.domain.entity;

import java.util.List;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(indexName = "realestate")
public class RealestateDocument {
    @Id
    private String id;
    private Long articleNo;
    private Long complexNo;
    private Long hscpNo;
    private Long ptpNo;
    private String tradeTypeCode;
    private String complexName;
    private String sidoName;
    private String guName;
    private String dongName;
    private String address;
    private Double latitude;
    private Double longitude;
    private String roomType;
    private String realEstateTypeName;
    private String floorInfo;
    private String buildingName;
    private String dealOrWarrantPrc;
    private String rentPrc;
    private Double supplyArea;
    private Double exclusiveArea;
    private Integer exclusiveRate;
    private String direction;
    private String tagList;
    private String description;
    private String realtorName;
    private String realtorAddress;
    private String realtorcellPhoneNo;
    private Long articleConfirmYmd; //
    private Integer roomCnt;
    private String bathroomCnt;
    private Long maintenanceFee;
    private String lawUsage;
    private String approvalDate;
    private Integer years_difference;
    private Long dealOrWarrantPrc_numeric;
    private Long rentPrc_numeric;
    private Object formatted_image_list;
    private Object realTransactionPriceList;

    @Override
    public String toString() {
        return "RealestateDocument{" +
            "id='" + id + '\'' +
            ", articleNo=" + articleNo +
            ", complexNo=" + complexNo +
            ", hscpNo=" + hscpNo +
            ", ptpNo=" + ptpNo +
            ", tradeTypeCode='" + tradeTypeCode + '\'' +
            ", complexName='" + complexName + '\'' +
            ", sidoName='" + sidoName + '\'' +
            ", guName='" + guName + '\'' +
            ", dongName='" + dongName + '\'' +
            ", address='" + address + '\'' +
            ", latitude=" + latitude +
            ", longitude=" + longitude +
            ", roomType='" + roomType + '\'' +
            ", realEstateTypeName='" + realEstateTypeName + '\'' +
            ", floorInfo='" + floorInfo + '\'' +
            ", buildingName='" + buildingName + '\'' +
            ", dealOrWarrantPrc='" + dealOrWarrantPrc + '\'' +
            ", rentPrc='" + rentPrc + '\'' +
            ", supplyArea=" + supplyArea +
            ", exclusiveArea=" + exclusiveArea +
            ", exclusiveRate=" + exclusiveRate +
            ", direction='" + direction + '\'' +
            ", tagList='" + tagList + '\'' +
            ", description='" + description + '\'' +
            ", realtorName='" + realtorName + '\'' +
            ", realtorAddress='" + realtorAddress + '\'' +
            ", realtorcellPhoneNo='" + realtorcellPhoneNo + '\'' +
            ", articleConfirmYmd=" + articleConfirmYmd +
            ", roomCnt=" + roomCnt +
            ", bathroomCnt='" + bathroomCnt + '\'' +
            ", maintenanceFee=" + maintenanceFee +
            ", lawUsage='" + lawUsage + '\'' +
            ", approvalDate='" + approvalDate + '\'' +
            ", years_difference=" + years_difference +
            ", dealOrWarrantPrc_numeric=" + dealOrWarrantPrc_numeric +
            ", rentPrc_numeric=" + rentPrc_numeric +
            ", formatted_image_list=" + formatted_image_list +
            ", realTransactionPriceList=" + realTransactionPriceList +
            '}';
    }
}
