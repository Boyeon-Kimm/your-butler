package com.ficrew.yourbutler.global.auth;

import lombok.Getter;

@Getter
public class Token {
    private String accessToken;
    private String refreshToken;

    public void removeRefresh() {
        this.refreshToken = null;
    }
    public Token(String accessToken) {
        this.accessToken = accessToken;
    }
    public Token(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
