package com.ficrew.yourbutler.member.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ficrew.yourbutler.global.auth.JWTProvider;
import com.ficrew.yourbutler.global.auth.PasswordEncrypter;
import com.ficrew.yourbutler.global.auth.Token;
import com.ficrew.yourbutler.member.application.command.SignInCommand;
import com.ficrew.yourbutler.member.application.result.GoogleLoginResult;
import com.ficrew.yourbutler.member.application.result.KakaoLoginResult;
import com.ficrew.yourbutler.member.application.result.NaverLoginResult;
import com.ficrew.yourbutler.member.application.result.SignInResult;
import com.ficrew.yourbutler.member.domain.entity.Member;
import com.ficrew.yourbutler.member.domain.exception.InvalidSocialTypeException;
import com.ficrew.yourbutler.member.domain.repository.MemberRepository;
import com.ficrew.yourbutler.member.presentation.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class SignInMemberProcessor {
    private final MemberRepository memberRepository;
    private final PasswordEncrypter passwordEncrypter;
    private final JWTProvider jwtProvider;
    private final String KAKAO_OAUTH2_URL = "https://kapi.kakao.com/v2/user/me";
    private final String NAVER_OAUTH2_URL = "https://openapi.naver.com/v1/nid/me";
    private final String GOOGLE_OAUTH2_URL = "https://www.googleapis.com/oauth2/v1/userinfo";
    private RestTemplate restTemplate = new RestTemplate();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Transactional
    public SignInResult execute(SignInCommand signInCommand) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + signInCommand.getToken());
        httpHeaders.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        EmailAndNickname emailAndNickname;

        HttpEntity<MultiValueMap<String, String>> profileRequest = new HttpEntity<>(httpHeaders);

        switch (signInCommand.getSocialType()) {
            case "KAKAO":
                emailAndNickname = kakao_login(profileRequest);
                break;
            case "GOOGLE":
                emailAndNickname = google_login(profileRequest);
                break;
            case "NAVER":
                emailAndNickname = naver_login(profileRequest);
                break;
            default:
                throw new InvalidSocialTypeException();
        }

        Member member;

        try {
            member = memberRepository.findBySocialTypeAndIdentifier(signInCommand.getSocialType(), emailAndNickname.identifier);
        } catch (JpaObjectRetrievalFailureException e) {
            member = new Member(
                            emailAndNickname.identifier,
                            signInCommand.getSocialType(),
                            emailAndNickname.email,
                            emailAndNickname.nickname,
                            passwordEncrypter.encrypt(emailAndNickname.identifier));

            memberRepository.save(member);
        }

        Token token = jwtProvider.generateTokens(
                member.getSocialType() + " " + member.getIdentifier(),
                member.getIdentifier()
        );

        token.removeRefresh();

        MemberResponse memberResponse = new MemberResponse(member);

        return new SignInResult(token, memberResponse);
    }

    private EmailAndNickname kakao_login(HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest) {
        ResponseEntity<String> response = restTemplate.exchange(
                KAKAO_OAUTH2_URL,
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        KakaoLoginResult kakaoLoginResult;
        try {
            kakaoLoginResult = objectMapper.readValue(response.getBody(), KakaoLoginResult.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return new EmailAndNickname(
                kakaoLoginResult.getKakaoAccount().getEmail(),
                kakaoLoginResult.getKakaoAccount().getEmail(),
                kakaoLoginResult.getProperties().getNickname()
        );
    }
    private EmailAndNickname naver_login(HttpEntity<MultiValueMap<String, String>> naverProfileRequest) {
        ResponseEntity<String> response = restTemplate.exchange(
                NAVER_OAUTH2_URL,
                HttpMethod.GET,
                naverProfileRequest,
                String.class
        );

        NaverLoginResult naverLoginResult;
        try {
            naverLoginResult = objectMapper.readValue(response.getBody(), NaverLoginResult.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return new EmailAndNickname(
                naverLoginResult.getResponse().getId(),
                naverLoginResult.getResponse().getEmail(),
                naverLoginResult.getResponse().getNickname()
        );
    }
    private EmailAndNickname google_login(HttpEntity<MultiValueMap<String, String>> googleProfileRequest) {
        ResponseEntity<String> response = restTemplate.exchange(
                GOOGLE_OAUTH2_URL,
                HttpMethod.GET,
                googleProfileRequest,
                String.class
        );

        GoogleLoginResult googleLoginResult;
        try {
            googleLoginResult = objectMapper.readValue(response.getBody(), GoogleLoginResult.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return new EmailAndNickname(
                googleLoginResult.getIdentifier(),
                googleLoginResult.getEmail(),
                googleLoginResult.getNickname()
        );
    }

    private class EmailAndNickname {
        String identifier, email, nickname;
        public EmailAndNickname(String identifier, String email, String nickname) {
            this.identifier = identifier;
            this.email = email;
            this.nickname = nickname;
        }
    }
}
