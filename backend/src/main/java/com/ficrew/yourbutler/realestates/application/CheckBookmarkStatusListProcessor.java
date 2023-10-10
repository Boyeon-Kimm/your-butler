package com.ficrew.yourbutler.realestates.application;

import com.ficrew.yourbutler.realestates.domain.entity.Bookmark;
import com.ficrew.yourbutler.realestates.domain.repository.BookmarkRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CheckBookmarkStatusListProcessor {

    private final BookmarkRepository bookmarkRepository;

    public Map<Long, Boolean> execute(List<Long> realestateId) {
        List<Bookmark> bookmarks = bookmarkRepository.findByRealestateIdIn(realestateId);
        return bookmarks.stream()
            .collect(Collectors.toMap(
                Bookmark::getRealestateId,
                b -> true,
                (a, b) -> b
            ));
    }
}
