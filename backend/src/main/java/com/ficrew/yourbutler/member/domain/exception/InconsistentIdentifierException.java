package com.ficrew.yourbutler.member.domain.exception;

import com.ficrew.yourbutler.global.exception.CustomBaseException;
import com.ficrew.yourbutler.global.exception.ErrorCode;

public class InconsistentIdentifierException extends CustomBaseException {
    public InconsistentIdentifierException(ErrorCode errorCode) {
        super(errorCode);
    }

    public InconsistentIdentifierException() {
        super(ErrorCode.INCONSISTENCY_IDENTIFIER);
    }
}
