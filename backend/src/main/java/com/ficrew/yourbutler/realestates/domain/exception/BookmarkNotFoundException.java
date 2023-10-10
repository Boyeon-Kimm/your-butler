package com.ficrew.yourbutler.realestates.domain.exception;

import com.ficrew.yourbutler.global.exception.CustomBaseException;
import com.ficrew.yourbutler.global.exception.ErrorCode;

public class BookmarkNotFoundException extends CustomBaseException {

    public BookmarkNotFoundException() {
        super(ErrorCode.BOOKMARK_NOT_FOUND);
    }
}
