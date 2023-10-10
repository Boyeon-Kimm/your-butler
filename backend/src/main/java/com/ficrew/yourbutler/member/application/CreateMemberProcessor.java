package com.ficrew.yourbutler.member.application;

import com.ficrew.yourbutler.global.auth.AuthenticatedMember;
import com.ficrew.yourbutler.member.application.command.CreateMemberCommand;
import com.ficrew.yourbutler.member.domain.entity.Member;
import com.ficrew.yourbutler.member.domain.exception.InconsistentIdentifierException;
import com.ficrew.yourbutler.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateMemberProcessor {

    private final MemberRepository memberRepository;

    public void execute(CreateMemberCommand command, AuthenticatedMember member) {
        if (!command.getIdentifier().equals(member.getIdentifier())) {
            throw new InconsistentIdentifierException();
        }

        Member findMember = memberRepository.findBySocialTypeAndIdentifier(member.getSocialType(), member.getIdentifier());
        findMember.update(command);

        memberRepository.save(findMember);
    }

}
