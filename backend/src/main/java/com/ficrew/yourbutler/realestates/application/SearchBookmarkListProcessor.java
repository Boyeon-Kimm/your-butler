package com.ficrew.yourbutler.realestates.application;

import com.ficrew.yourbutler.realestates.domain.entity.Bookmark;
import com.ficrew.yourbutler.realestates.domain.entity.RealestateDocument;
import com.ficrew.yourbutler.realestates.domain.repository.BookmarkRepository;
import com.ficrew.yourbutler.realestates.infrastructure.persistence.es.ElasticsearchRealestateRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SearchBookmarkListProcessor {
    private final ElasticsearchRealestateRepository elasticsearchRealestateRepository;
    private final BookmarkRepository bookmarkRepository;

    public List<RealestateDocument> execute(Long memberId) {
        List<Bookmark> bookmarkList = bookmarkRepository.findByMemberId(memberId);

        List<RealestateDocument> result = new ArrayList<>();
        for (Bookmark bookmark : bookmarkList) {
            result.add(elasticsearchRealestateRepository.findByArticleNo(bookmark.getRealestateId()));
        }
        return result;
    }
}
