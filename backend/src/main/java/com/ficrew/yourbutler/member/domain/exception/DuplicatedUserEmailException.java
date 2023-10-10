package com.ficrew.yourbutler.member.domain.exception;

import com.ficrew.yourbutler.global.exception.CustomBaseException;
import com.ficrew.yourbutler.global.exception.ErrorCode;

public class DuplicatedUserEmailException extends CustomBaseException {

    public DuplicatedUserEmailException(ErrorCode errorCode) {
        super(errorCode);
    }

    public DuplicatedUserEmailException() {
        super(ErrorCode.DUPLICATED_EMAIL);
    }
}
