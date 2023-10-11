package com.ficrew.yourbutler.chat.domain.exception;

import com.ficrew.yourbutler.global.exception.CustomBaseException;
import com.ficrew.yourbutler.global.exception.ErrorCode;

public class ChatroomNotFoundException extends CustomBaseException {

    public ChatroomNotFoundException() { super(ErrorCode.CHATROOM_NOT_FOUND); }

}
