package com.ficrew.yourbutler.realestates.presentation;

import com.ficrew.yourbutler.global.auth.AuthenticatedMember;
import com.ficrew.yourbutler.member.domain.entity.NumberOfHouses;
import com.ficrew.yourbutler.realestates.application.command.MemberCalCommand;
import com.ficrew.yourbutler.realestates.application.facade.RealestateFacade;
import com.ficrew.yourbutler.realestates.application.command.SearchCommand;
import com.ficrew.yourbutler.realestates.application.facade.RealestateEsFacade;
import com.ficrew.yourbutler.realestates.domain.entity.RoomType;
import com.ficrew.yourbutler.realestates.domain.entity.TradeType;
import com.ficrew.yourbutler.realestates.domain.entity.RealestateDocument;
import com.ficrew.yourbutler.realestates.presentation.response.BookmarkCheckResponse;
import com.ficrew.yourbutler.realestates.presentation.response.BookmarkListResponse;
import com.ficrew.yourbutler.realestates.presentation.response.BookmarkStatusResponse;
import com.ficrew.yourbutler.realestates.presentation.response.RealestateDetailResponse;
import com.ficrew.yourbutler.realestates.presentation.response.RecentRealestateListResponse;
import com.ficrew.yourbutler.realestates.presentation.response.RecentRealestateResponse;
import com.ficrew.yourbutler.realestates.presentation.response.SearchBookmarkResponse;
import com.ficrew.yourbutler.realestates.presentation.response.SearchListResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/realestates")
@RequiredArgsConstructor
public class RealestateController {

    private final RealestateEsFacade realestateEsFacade;
    private final RealestateFacade realestateFacade;

    @GetMapping("/recent")
    public ResponseEntity<RecentRealestateListResponse> getRecentRealestate() {
        RecentRealestateListResponse results = RecentRealestateListResponse.from(
            realestateEsFacade.getRecentRealestates());
        return ResponseEntity.ok(results);
    }

    // 파라미터 값은 모두 만원 단위로 들어온다!
    @GetMapping("/search")
    public ResponseEntity<SearchListResponse> search(
        @AuthenticationPrincipal AuthenticatedMember member,
        @RequestParam(value = "size", defaultValue = "10") Integer size,
        @RequestParam(value = "from", defaultValue = "0") Integer from,
        @RequestParam(value = "keyword", required = false) String keyword,
        @RequestParam(value = "realestate-asset") Long realestateAsset,
        @RequestParam(value = "monthly-asset") Long monthlyAvailableAsset,
        @RequestParam(value = "trade-type", defaultValue = "RENT") TradeType tradeType,
        @RequestParam(value = "room-type", defaultValue = "APT") List<RoomType> roomTypeList,
        @RequestParam(value = "dw-min", defaultValue = "0") Long dwMin,
        @RequestParam(value = "dw-max", defaultValue = "9000000") Long dwMax,
        @RequestParam(value = "dp-min", defaultValue = "0") Long dpMin,
        @RequestParam(value = "dp-max", defaultValue = "9000000") Long dpMax,
        @RequestParam(value = "rp-min", defaultValue = "0") Long rpMin,
        @RequestParam(value = "rp-max", defaultValue = "9000000") Long rpMax,
        @RequestParam(value = "mf-min", defaultValue = "0") Long mfMin,
        @RequestParam(value = "mf-max", defaultValue = "9000000") Long mfMax,
        @RequestParam(value = "rs-min", defaultValue = "0") Long rsMin,
        @RequestParam(value = "rs-max", defaultValue = "9000000") Long rsMax,
        @RequestParam(value = "uay", defaultValue = "16") Integer uay
    ) {

        Page<RealestateDocument> searchResults = realestateEsFacade.searchProperties(
            new SearchCommand(
                size,
                from,
                keyword,
                realestateAsset * 10000,
                monthlyAvailableAsset * 10000,
                tradeType,
                roomTypeList,
                dwMin * 10000,
                dwMax * 10000,
                dpMin * 10000,
                dpMax * 10000,
                rpMin * 10000,
                rpMax * 10000,
                mfMin * 10000,
                mfMax * 10000,
                rsMin,
                rsMax,
                uay
            )
        );

        List<Long> articleNos = searchResults.getContent()
            .stream()
            .map(RealestateDocument::getArticleNo)
            .collect(Collectors.toList());

        BookmarkCheckResponse bookmarkCheckResponse;
        List<SearchBookmarkResponse> searchBookmarkResponseList = new ArrayList<>();

        if (member == null) {
            bookmarkCheckResponse = new BookmarkCheckResponse(false, false);
            for (RealestateDocument doc : searchResults.getContent()) {
                String color = realestateFacade.calculate(realestateAsset * 10000, monthlyAvailableAsset * 10000, doc, new MemberCalCommand(600, NumberOfHouses.NONE));
                searchBookmarkResponseList.add(
                    SearchBookmarkResponse.from(doc, color, bookmarkCheckResponse));
            }
        } else {
            Map<Long, Boolean> bookmarkedMap = realestateFacade.isBookmarkedList(articleNos);
            for (RealestateDocument doc : searchResults.getContent()) {
                Long articleNo = doc.getArticleNo();
                String color = realestateFacade.calculate(realestateAsset * 10000, monthlyAvailableAsset * 10000, doc, new MemberCalCommand(member.getCreditRating(), member.getNumberOfHouses()));
                Boolean isBookmarked = bookmarkedMap.getOrDefault(articleNo, false);
                searchBookmarkResponseList.add(SearchBookmarkResponse.from(doc, color,
                    new BookmarkCheckResponse(true, isBookmarked)));
            }
        }

        SearchListResponse finalResults = SearchListResponse.from(
            searchResults.getTotalElements(),
            searchResults.getTotalPages(),
            size,
            from,
            searchBookmarkResponseList
        );

        return ResponseEntity.ok(finalResults);
    }

    @GetMapping("/{realestateId}")
    public ResponseEntity<RealestateDetailResponse> searchByArticleNo(
        @AuthenticationPrincipal AuthenticatedMember member,
        @PathVariable Long realestateId
    ) {
        BookmarkCheckResponse bookmarkCheckResponse;
        if (member == null) {
            bookmarkCheckResponse = new BookmarkCheckResponse(false, false);
        } else {
            bookmarkCheckResponse = new BookmarkCheckResponse(true,
                realestateFacade.isBookmarked(realestateId));
        }
        RealestateDetailResponse result = RealestateDetailResponse.from(
            realestateEsFacade.searchDetails(realestateId), bookmarkCheckResponse);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/{realestateId}/check")
    public ResponseEntity<BookmarkStatusResponse> checkBoookmark(
        @AuthenticationPrincipal AuthenticatedMember member,
        @PathVariable Long realestateId
    ) {
        return ResponseEntity.ok(BookmarkStatusResponse.from(
            realestateFacade.createBookmark(member.getId(), realestateId)));
    }

    @DeleteMapping("/{realestateId}/uncheck")
    public ResponseEntity<BookmarkStatusResponse> uncheckBookmark(
        @AuthenticationPrincipal AuthenticatedMember member,
        @PathVariable Long realestateId
    ) {
        realestateFacade.deleteBookmark(member.getId(), realestateId);
        return ResponseEntity.ok(BookmarkStatusResponse.from(false));
    }

    @GetMapping("/bookmarks")
    public ResponseEntity<BookmarkListResponse> getBookmarkList(
        @AuthenticationPrincipal AuthenticatedMember member
    ) {
        return ResponseEntity.ok(
            BookmarkListResponse.from(realestateEsFacade.getBookmarkedList(member.getId())));
    }

}
