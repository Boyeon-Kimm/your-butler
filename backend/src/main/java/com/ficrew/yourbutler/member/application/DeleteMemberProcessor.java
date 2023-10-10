package com.ficrew.yourbutler.member.application;

import com.ficrew.yourbutler.global.auth.AuthenticatedMember;
import com.ficrew.yourbutler.member.domain.entity.Member;
import com.ficrew.yourbutler.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteMemberProcessor {

    private final MemberRepository memberRepository;

    public void execute(AuthenticatedMember member) {
        Member deleteMember = memberRepository.findById(member.getId());
        memberRepository.delete(deleteMember);
    }

}
