package com.ficrew.yourbutler.realestates.infrastructure.persistence;

import com.ficrew.yourbutler.realestates.domain.exception.BookmarkNotFoundException;
import com.ficrew.yourbutler.realestates.domain.repository.BookmarkRepository;
import com.ficrew.yourbutler.realestates.domain.entity.Bookmark;
import com.ficrew.yourbutler.realestates.infrastructure.persistence.jpa.JpaBookmarkRepository;
import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RealestateRepositoryAdapter implements BookmarkRepository {
    private final JpaBookmarkRepository jpaBookmarkRepository;

    @Override
    public boolean existsByRealestateId(Long realestateId) {
        return jpaBookmarkRepository.existsByRealestateId(realestateId);
    }

    @Override
    public Bookmark save(Bookmark bookmark) {
        return jpaBookmarkRepository.save(bookmark);
    }

    @Override
    public boolean deleteByRealestateIdAndMemberId(Long realestateId, Long memberId) {
        return jpaBookmarkRepository.deleteByRealestateIdAndMemberId(realestateId, memberId) != 1L;
    }

    @Override
    public List<Bookmark> findByMemberId(Long memberId) {
        return jpaBookmarkRepository.findByMemberId(memberId);
    }

    @Override
    public boolean existsByRealestateIdAndMemberId(Long realestateId, Long memberId) {
        return jpaBookmarkRepository.existsByRealestateIdAndMemberId(realestateId, memberId);
    }

    @Override
    public Bookmark findByRealestateIdAndMemberId(Long realestateId, Long memberId) {
        return jpaBookmarkRepository
            .findByRealestateIdAndMemberId(realestateId, memberId)
            .orElseThrow(BookmarkNotFoundException::new);
    }

    @Override
    public void delete(Bookmark bookmark) {
        jpaBookmarkRepository.delete(bookmark);
    }

    @Override
    public List<Bookmark> findByRealestateIdIn(List<Long> realestateId) {
        return jpaBookmarkRepository.findByRealestateIdIn(realestateId);
    }
}
