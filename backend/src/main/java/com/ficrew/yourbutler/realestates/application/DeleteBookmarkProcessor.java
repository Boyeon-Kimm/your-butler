package com.ficrew.yourbutler.realestates.application;

import com.ficrew.yourbutler.realestates.domain.entity.Bookmark;
import com.ficrew.yourbutler.realestates.domain.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteBookmarkProcessor {

    private final BookmarkRepository bookmarkRepository;
    public void execute(Long memberId, Long realestateId) {
        Bookmark bookmark = bookmarkRepository.findByRealestateIdAndMemberId(
            realestateId, memberId
        );
        bookmarkRepository.delete(bookmark);
    }
}
