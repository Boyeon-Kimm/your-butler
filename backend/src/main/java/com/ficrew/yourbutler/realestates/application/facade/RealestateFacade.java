package com.ficrew.yourbutler.realestates.application.facade;

import com.ficrew.yourbutler.realestates.application.CalculateColorProcessor;
import com.ficrew.yourbutler.realestates.application.CheckBookmarkStatusListProcessor;
import com.ficrew.yourbutler.realestates.application.CheckBookmarkStatusProcessor;
import com.ficrew.yourbutler.realestates.application.CreateBookmarkProcessor;
import com.ficrew.yourbutler.realestates.application.DeleteBookmarkProcessor;
import com.ficrew.yourbutler.realestates.application.command.MemberCalCommand;
import com.ficrew.yourbutler.realestates.domain.entity.RealestateDocument;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RealestateFacade {

    private final CheckBookmarkStatusProcessor checkBookmarkStatusProcessor;
    private final CreateBookmarkProcessor createBookmarkProcessor;
    private final DeleteBookmarkProcessor deleteBookmarkProcessor;
    private final CheckBookmarkStatusListProcessor checkBookmarkStatusListProcessor;
    private final CalculateColorProcessor calculateColorProcessor;

    public boolean isBookmarked(Long realestateId) {
        return checkBookmarkStatusProcessor.execute(realestateId);
    }

    @Transactional
    public boolean createBookmark(Long memberId, Long realestateId) {
        return createBookmarkProcessor.execute(memberId, realestateId);
    }

    @Transactional
    public void deleteBookmark(Long memberId, Long realestateId) {
        deleteBookmarkProcessor.execute(memberId, realestateId);
    }

    public Map<Long, Boolean> isBookmarkedList(List<Long> articleNos) {
        return checkBookmarkStatusListProcessor.execute(articleNos);
    }

    public String calculate(Long realestateAsset, Long monthlyAvailableAsset, RealestateDocument doc, MemberCalCommand command) {
        return calculateColorProcessor.execute(realestateAsset, monthlyAvailableAsset, doc, doc.getRealEstateTypeName(), command);
    }


}
