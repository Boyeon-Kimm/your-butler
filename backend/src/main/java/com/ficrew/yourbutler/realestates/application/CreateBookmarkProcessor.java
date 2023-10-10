package com.ficrew.yourbutler.realestates.application;

import com.ficrew.yourbutler.member.domain.repository.MemberRepository;
import com.ficrew.yourbutler.realestates.domain.repository.BookmarkRepository;
import com.ficrew.yourbutler.realestates.domain.entity.Bookmark;
import com.ficrew.yourbutler.realestates.domain.exception.AlreadyExistBookmarkException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateBookmarkProcessor {
    private final BookmarkRepository bookmarkRepository;
    private final MemberRepository memberRepository;

    public boolean execute(Long memberId, Long realestateId) {
        if (bookmarkRepository.existsByRealestateIdAndMemberId(realestateId, memberId)) {
            throw new AlreadyExistBookmarkException();
        }

        Bookmark savedBookmark = bookmarkRepository.save(
            new Bookmark(
                memberRepository.findById(memberId),
                realestateId
            )
        );
        return savedBookmark != null;
    }
}
