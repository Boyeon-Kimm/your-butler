package com.ficrew.yourbutler.realestates.domain.service;

import com.ficrew.yourbutler.member.domain.entity.NumberOfHouses;
import com.ficrew.yourbutler.realestates.domain.entity.RealestateDocument;

public interface CalculateService {

    String deal(Long realestateAsset, Long monthlyAvailableAsset, RealestateDocument doc, Integer creditRating, NumberOfHouses numberOfHouses);

    String lease(Long realestateAsset, Long monthlyAvailableAsset, RealestateDocument doc, Integer creditRating, NumberOfHouses numberOfHouses);

    String rent(Long realestateAsset, Long monthlyAvailableAsset, RealestateDocument doc, Integer creditRating, NumberOfHouses numberOfHouses);
}
