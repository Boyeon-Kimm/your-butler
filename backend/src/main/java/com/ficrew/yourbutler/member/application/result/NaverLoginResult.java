package com.ficrew.yourbutler.member.application.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverLoginResult {

    private Response response;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Response {
        String id, nickname, email;
    }
}
