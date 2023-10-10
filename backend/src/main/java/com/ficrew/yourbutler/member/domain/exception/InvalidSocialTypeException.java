package com.ficrew.yourbutler.member.domain.exception;

import com.ficrew.yourbutler.global.exception.CustomBaseException;
import com.ficrew.yourbutler.global.exception.ErrorCode;

public class InvalidSocialTypeException extends CustomBaseException {
    public InvalidSocialTypeException() {
        super(ErrorCode.INVALID_SOCIAL_TYPE);
    }
    public InvalidSocialTypeException(ErrorCode errorCode) {
        super(errorCode);
    }
}
