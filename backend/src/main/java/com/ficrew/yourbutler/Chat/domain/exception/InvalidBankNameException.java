package com.ficrew.yourbutler.Chat.domain.exception;

import com.ficrew.yourbutler.global.exception.CustomBaseException;
import com.ficrew.yourbutler.global.exception.ErrorCode;

public class InvalidBankNameException extends CustomBaseException {

    public InvalidBankNameException() { super(ErrorCode.INVALID_BANK_NAME); }

}
