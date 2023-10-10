package com.ficrew.yourbutler.member.application;

import com.ficrew.yourbutler.global.auth.AuthenticatedMember;
import com.ficrew.yourbutler.member.application.command.EditMemberCommand;
import com.ficrew.yourbutler.member.domain.entity.Member;
import com.ficrew.yourbutler.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EditMemberProcessor {

    private final MemberRepository memberRepository;

    public void execute(EditMemberCommand command, AuthenticatedMember member) {
        Member editMember = memberRepository.findById(member.getId());
        editMember.edit(command);
        memberRepository.save(editMember);
    }

}
