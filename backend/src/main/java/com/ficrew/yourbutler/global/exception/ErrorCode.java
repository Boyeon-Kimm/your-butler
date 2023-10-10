package com.ficrew.yourbutler.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    /*
     * Global
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "GLOBAL_INTERNAL_SERVER_ERROR",
        "내부 서버 에러가 발생했습니다."),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "GLOBAL_INVALID_INPUT_VALUE", "올바르지 않은 입력값입니다."),
    NOT_SUPPORTED_METHOD_ERROR(HttpStatus.METHOD_NOT_ALLOWED, "GLOBAL_NOT_SUPPORTED_METHOD_ERROR",
        "지원하지 않는 Method 요청입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "GLOBAL_404_ERROR", "404 에러입니다. 요청한 데이터를 서버가 찾을 수 없습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "GLOBAL_METHOD_NOT_ALLOWED",
        "잘못된 HTTP 메서드를 호출했습니다."),

    /*
     * User
     */
    DUPLICATED_EMAIL(HttpStatus.BAD_REQUEST, "USER_DUPLICATED_EMAIL", "이미 존재하는 이메일입니다."),
    INCONSISTENCY_IDENTIFIER(HttpStatus.BAD_REQUEST, "IDENTIFIER_IS_INCONSISTENT",
        "입력된 식별자와 토큰이 일치하지 않습니다."),
    INVALID_SOCIAL_TYPE(HttpStatus.BAD_REQUEST, "SOCIAL_TYPE_IS_INVALID", "소셜 타입을 확인해주세요."),

    /*
     * Chat
     */
    CHATROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "CHATROOM_NOT_FOUND", "요청한 채팅방을 찾을 수 없습니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER_NOT_FOUND", "요청한 회원을 찾을 수 없습니다."),
    FLASK_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "FLASK_SERVER_ERROR", "Flask 서버로부터 응답이 없습니다."),
    INVALID_CHATROOM_NUMBER(HttpStatus.BAD_REQUEST, "INVALID_CHATROOM_NUMBER", "올바르지 않은 채팅방 번호입니다."),
    INVALID_BANK_NAME(HttpStatus.BAD_REQUEST, "INVALID_BANK_NAME", "올바르지 않은 은행 이름입니다."),

    /*
     * Realestate
     */
    ALREADY_EXIST_BOOKMARK(HttpStatus.BAD_REQUEST, "ALREADY_EXISTS_BOOKMARK", "이미 존재하고 있는 북마크입니다."),
    BOOKMARK_NOT_FOUND(HttpStatus.NOT_FOUND, "NOT_FOUND_BOOKMARK", "생성한 적이 없는 북마크이므로 삭제할 수 없습니다.");




    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
