package com.ficrew.yourbutler.member.application.command;

import lombok.Getter;

@Getter
public class SignInCommand {
    String socialType;
    String token;

    public SignInCommand(String socialType, String token) {
        this.socialType = socialType;
        this.token = token;
    }
}
