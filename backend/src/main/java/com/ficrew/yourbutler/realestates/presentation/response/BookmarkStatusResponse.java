package com.ficrew.yourbutler.realestates.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookmarkStatusResponse {
    private boolean isChecked;

    public static BookmarkStatusResponse from(boolean isChecked) {
        return new BookmarkStatusResponse(isChecked);
    }
}
