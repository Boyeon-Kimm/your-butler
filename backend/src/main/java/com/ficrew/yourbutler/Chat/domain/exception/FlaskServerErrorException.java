package com.ficrew.yourbutler.chat.domain.exception;

import com.ficrew.yourbutler.global.exception.CustomBaseException;
import com.ficrew.yourbutler.global.exception.ErrorCode;

public class FlaskServerErrorException extends CustomBaseException {

    public FlaskServerErrorException() { super(ErrorCode.FLASK_SERVER_ERROR); }

}
