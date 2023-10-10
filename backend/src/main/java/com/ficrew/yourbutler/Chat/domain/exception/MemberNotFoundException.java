package com.ficrew.yourbutler.Chat.domain.exception;

import com.ficrew.yourbutler.global.exception.CustomBaseException;
import com.ficrew.yourbutler.global.exception.ErrorCode;

public class MemberNotFoundException extends CustomBaseException {

    public MemberNotFoundException() { super(ErrorCode.MEMBER_NOT_FOUND); }

}
