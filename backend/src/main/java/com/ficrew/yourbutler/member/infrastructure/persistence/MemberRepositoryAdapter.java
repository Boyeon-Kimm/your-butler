package com.ficrew.yourbutler.member.infrastructure.persistence;

import com.ficrew.yourbutler.member.domain.entity.Member;
import com.ficrew.yourbutler.member.domain.repository.MemberRepository;
import com.ficrew.yourbutler.member.infrastructure.persistence.jpa.JpaMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryAdapter implements MemberRepository {

    private final JpaMemberRepository memberRepository;

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member findBySocialTypeAndIdentifier(String socialType, String identifier) {
        return memberRepository.findBySocialTypeAndIdentifier(socialType, identifier)
                .orElseThrow(()-> new EntityNotFoundException("Member Entity Not found."));
    }

    @Override
    public boolean existsByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    @Override
    public Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Member Entity Not found."));
    }

    @Override
    public void delete(Member member) {
        memberRepository.delete(member);
    }

}
