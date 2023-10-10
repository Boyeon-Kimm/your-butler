package com.ficrew.yourbutler.realestates.domain.repository;

import com.ficrew.yourbutler.realestates.domain.entity.RealestateDocument;
import java.util.List;

public interface SearchRepository {

    RealestateDocument findByRealestateId(Long realestateId);
    List<RealestateDocument> searchRecentDeal2();
    List<RealestateDocument> searchRecentRent2();

    List<RealestateDocument> searchRecentLease1();

//    boolean existsByRealestateId(Long realestateId);
}
