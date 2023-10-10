package com.ficrew.yourbutler.realestates.domain.exception;

import com.ficrew.yourbutler.global.exception.CustomBaseException;
import com.ficrew.yourbutler.global.exception.ErrorCode;

public class AlreadyExistBookmarkException extends CustomBaseException {

    public AlreadyExistBookmarkException() {
        super(ErrorCode.ALREADY_EXIST_BOOKMARK);
    }
}
