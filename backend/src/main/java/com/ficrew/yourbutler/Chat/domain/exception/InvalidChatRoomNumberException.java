package com.ficrew.yourbutler.Chat.domain.exception;

import com.ficrew.yourbutler.global.exception.CustomBaseException;
import com.ficrew.yourbutler.global.exception.ErrorCode;

public class InvalidChatRoomNumberException extends CustomBaseException {

    public InvalidChatRoomNumberException() { super(ErrorCode.INVALID_CHATROOM_NUMBER); }

}
