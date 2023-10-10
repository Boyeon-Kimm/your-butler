package com.ficrew.yourbutler.realestates.presentation.response;

import com.ficrew.yourbutler.realestates.domain.entity.RealestateDocument;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookmarkListResponse {
    List<BookmarkResponse> boomarkList;

    public static BookmarkListResponse from(List<RealestateDocument> bookmarkedList) {
        return new BookmarkListResponse(
            bookmarkedList.stream()
                .map(BookmarkResponse::from)
                .collect(Collectors.toList())
        );
    }

    @Getter
    @AllArgsConstructor
    private static class BookmarkResponse {
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


        public static BookmarkResponse from(RealestateDocument realestateDocument) {
            return new BookmarkResponse(
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
                realestateDocument.getMaintenanceFee(),
                realestateDocument.getFormatted_image_list()
            );
        }
    }
}
