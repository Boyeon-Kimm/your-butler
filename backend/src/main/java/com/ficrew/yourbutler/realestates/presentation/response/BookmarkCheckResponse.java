package com.ficrew.yourbutler.realestates.presentation.response;

import com.ficrew.yourbutler.realestates.domain.entity.Bookmark;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookmarkCheckResponse {
    private boolean isLogin;
    private boolean isChecked;


}
