package com.ficrew.yourbutler.realestates.presentation.response;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SearchListResponse {

    private final Long totalElements;
    private final Integer totalPages;
    private final Integer size;
    private final Integer from;
    private final List<SearchBookmarkResponse> roomTypeList;

    public static SearchListResponse from(long totalElements, int totalPages, Integer size, Integer from, List<SearchBookmarkResponse> searchBookmarkResponses) {
        return new SearchListResponse(
            totalElements,
            totalPages,
            size,
            from,
            searchBookmarkResponses
        );
    }




}
