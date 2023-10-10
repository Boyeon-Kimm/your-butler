package com.ficrew.yourbutler.member.application.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleLoginResult {

    @JsonProperty("id")
    private String identifier;

    @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String nickname;

}
