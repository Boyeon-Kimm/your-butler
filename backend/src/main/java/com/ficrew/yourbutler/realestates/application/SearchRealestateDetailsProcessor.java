package com.ficrew.yourbutler.realestates.application;

import com.ficrew.yourbutler.realestates.domain.entity.RealestateDocument;
import com.ficrew.yourbutler.realestates.domain.repository.SearchRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SearchRealestateDetailsProcessor {

    private final SearchRepository searchRepository;

    public RealestateDocument execute(Long realestateId) {
        return searchRepository.findByRealestateId(realestateId);
    }
}
