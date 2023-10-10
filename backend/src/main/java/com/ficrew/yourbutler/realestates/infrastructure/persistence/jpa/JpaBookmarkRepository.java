package com.ficrew.yourbutler.realestates.infrastructure.persistence.jpa;

import com.ficrew.yourbutler.realestates.domain.entity.Bookmark;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBookmarkRepository extends JpaRepository<Bookmark, Long> {

    boolean existsByRealestateId(Long realestateId);

    Long deleteByRealestateIdAndMemberId(Long realestateId, Long memberId);

    List<Bookmark> findByMemberId(Long memberId);

    boolean existsByRealestateIdAndMemberId(Long realestateId, Long memberId);

    Optional<Bookmark> findByRealestateIdAndMemberId(Long realestateId, Long memberId);

    List<Bookmark> findByRealestateIdIn(List<Long> realestateId);
}
