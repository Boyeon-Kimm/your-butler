package com.ficrew.yourbutler.member.application.result;

import com.ficrew.yourbutler.global.auth.Token;
import com.ficrew.yourbutler.member.presentation.response.MemberResponse;
import com.ficrew.yourbutler.member.presentation.response.SignInResponse;

public class SignInResult {

    Token token;
    MemberResponse memberResponse;
    public SignInResult(Token token, MemberResponse memberResponse) {
        this.token = token;
        this.memberResponse = memberResponse;
    }

    public SignInResponse toResponse() {
        return new SignInResponse(token, memberResponse);
    }

}
