package com.ficrew.yourbutler.realestates.application;

import com.ficrew.yourbutler.realestates.domain.entity.RealestateDocument;
import com.ficrew.yourbutler.realestates.domain.repository.SearchRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetRecentListProcessor {

    private final SearchRepository searchRepository;


    public List<RealestateDocument> execute() {
        List<RealestateDocument> result = new ArrayList<>();

        result.addAll(searchRepository.searchRecentRent2());
        result.addAll(searchRepository.searchRecentDeal2());
        result.addAll(searchRepository.searchRecentLease1());

        return result;
    }
}
