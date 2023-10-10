package com.ficrew.yourbutler.member.presentation;

import com.ficrew.yourbutler.global.auth.AuthenticatedMember;
import com.ficrew.yourbutler.member.application.facade.MemberFacade;
import com.ficrew.yourbutler.member.domain.entity.Member;
import com.ficrew.yourbutler.member.presentation.request.CreateMemberRequest;
import com.ficrew.yourbutler.member.presentation.request.EditMemberRequest;
import com.ficrew.yourbutler.member.presentation.request.SignInRequest;
import com.ficrew.yourbutler.member.presentation.response.SignInResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberFacade memberFacade;

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> signin(
            @RequestBody @Valid SignInRequest signInRequest
    ) {
        SignInResponse signInResponse = memberFacade.signInMember(signInRequest.toCommand()).toResponse();
        return new ResponseEntity<>(signInResponse, signInResponse.isActivated() ? HttpStatus.OK : HttpStatus.ACCEPTED);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(
        @RequestBody @Valid CreateMemberRequest request,
        @AuthenticationPrincipal AuthenticatedMember member
    ) {
        memberFacade.createMember(request.toCommand(), member);
        return new ResponseEntity<>("회원가입 완료", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> edit(
        @RequestBody @Valid EditMemberRequest request,
        @AuthenticationPrincipal AuthenticatedMember member
    ) {
        memberFacade.editMember(request.toCommand(), member);
        return new ResponseEntity<>("회원정보 수정 완료", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(
        @AuthenticationPrincipal AuthenticatedMember member
    ) {
        memberFacade.deleteMember(member);
        return new ResponseEntity<>("회원탈퇴 완료", HttpStatus.OK);
    }

    // AuthenticationMember는 getEmail 작동 하는데 그냥 Member는 작동 안함
    // DB에서 ID값 변경하고 찍어보면 변경된 ID값 가져오는거 보면 일단 DB를 갔다오는거는 맞는것 같음
    // DB 조회 2번시키면 되기는 하는데... 좀 구림
    @GetMapping("/test")
    public void edit(@AuthenticationPrincipal AuthenticatedMember member) {
        System.out.println(member.getId());
        System.out.println(member);
    }

}
