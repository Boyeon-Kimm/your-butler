package com.ficrew.yourbutler.member.infrastructure.persistence.jpa;

import com.ficrew.yourbutler.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaMemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
    Optional<Member> findBySocialTypeAndIdentifier(String socialType, String identifier);
    boolean existsByEmail(String email);
    Optional<Member> findById(Long id);

    // member 변수를 받아 삭제하는 연산
    void delete(Member member);


}
