package com.ficrew.yourbutler.realestates.application;

import com.ficrew.yourbutler.realestates.domain.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CheckBookmarkStatusProcessor {

    private final BookmarkRepository bookmarkRepository;

    public boolean execute(Long realestateId) {
        return bookmarkRepository.existsByRealestateId(realestateId);
    }

}
