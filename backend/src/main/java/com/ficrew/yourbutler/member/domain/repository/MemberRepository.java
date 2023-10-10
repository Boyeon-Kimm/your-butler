package com.ficrew.yourbutler.member.domain.repository;

import com.ficrew.yourbutler.member.domain.entity.Member;

import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    Member findBySocialTypeAndIdentifier(String socialType, String identifier);

    boolean existsByEmail(String email);

    Member findById(Long id);

    void delete(Member member);

}
