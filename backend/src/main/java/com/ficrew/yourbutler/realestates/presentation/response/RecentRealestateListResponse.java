package com.ficrew.yourbutler.realestates.presentation.response;

import com.ficrew.yourbutler.realestates.domain.entity.RealestateDocument;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecentRealestateListResponse {

    private final List<RecentRealestateResponse> recentList;

    public static RecentRealestateListResponse from(List<RealestateDocument> recentRealestates) {
        List<RecentRealestateResponse> responses = recentRealestates.stream()
            .map(RecentRealestateResponse::from)
            .collect(Collectors.toList());
        return new RecentRealestateListResponse(responses);
    }


    @Getter
    @AllArgsConstructor
    public static class RecentRealestateResponse {

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
        private final Long maintenanceFee;
        private final Object imageSrc;
        private final Long articleConfirmYmd;

        private static RecentRealestateResponse from(RealestateDocument doc) {
            return new RecentRealestateResponse(
                doc.getArticleNo(),
                doc.getRoomType(),
                doc.getComplexName(),
                doc.getRealEstateTypeName(),
                doc.getSidoName(),
                doc.getGuName(),
                doc.getDongName(),
                doc.getAddress(),
                doc.getLatitude(),
                doc.getLongitude(),
                doc.getTagList(),
                doc.getDealOrWarrantPrc(),
                doc.getRentPrc(),
                doc.getMaintenanceFee(),
                doc.getFormatted_image_list(),
                doc.getArticleConfirmYmd()
            );
        }

    }


}
