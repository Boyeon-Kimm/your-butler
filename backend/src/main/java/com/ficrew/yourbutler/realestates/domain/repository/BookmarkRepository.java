package com.ficrew.yourbutler.realestates.domain.repository;

import com.ficrew.yourbutler.realestates.domain.entity.Bookmark;
import java.util.List;
import java.util.Optional;

public interface BookmarkRepository {

    boolean existsByRealestateId(Long realestateId);

    Bookmark save(Bookmark bookmark);

    boolean deleteByRealestateIdAndMemberId(Long realestateId, Long memberId);

    List<Bookmark> findByMemberId(Long memberId);

    boolean existsByRealestateIdAndMemberId(Long realestateId, Long memberId);

    Bookmark findByRealestateIdAndMemberId(Long realestateId, Long memberId);

    void delete(Bookmark bookmark);

    List<Bookmark> findByRealestateIdIn(List<Long> realestateId);
}
