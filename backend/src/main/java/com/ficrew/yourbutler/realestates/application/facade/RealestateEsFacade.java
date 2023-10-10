package com.ficrew.yourbutler.realestates.application.facade;

import com.ficrew.yourbutler.realestates.application.GetRecentListProcessor;
import com.ficrew.yourbutler.realestates.application.SearchAllRealestatesProcessor;
import com.ficrew.yourbutler.realestates.application.SearchBookmarkListProcessor;
import com.ficrew.yourbutler.realestates.application.SearchRealestateDetailsProcessor;
import com.ficrew.yourbutler.realestates.domain.entity.RealestateDocument;
import com.ficrew.yourbutler.realestates.application.command.SearchCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RealestateEsFacade {
    private final SearchRealestateDetailsProcessor searchRealestateDetailsProcessor;
    private final SearchAllRealestatesProcessor searchAllRealestatesProcessor;
    private final SearchBookmarkListProcessor getBookmarkListProcessor;
    private final GetRecentListProcessor getRecentListProcessor;

    public Page<RealestateDocument> searchProperties(SearchCommand command) {
        return searchAllRealestatesProcessor.execute(command);
    }

    public RealestateDocument searchDetails(Long realestateId) {
        return searchRealestateDetailsProcessor.execute(realestateId);
    }

    public List<RealestateDocument> getBookmarkedList(Long memberId) {
        return getBookmarkListProcessor.execute(memberId);
    }

    public List<RealestateDocument> getRecentRealestates() {
        return getRecentListProcessor.execute();
    }
}
