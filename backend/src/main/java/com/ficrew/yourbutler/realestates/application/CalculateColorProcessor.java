package com.ficrew.yourbutler.realestates.application;

import com.ficrew.yourbutler.realestates.application.command.MemberCalCommand;
import com.ficrew.yourbutler.realestates.domain.service.CalculateService;
import com.ficrew.yourbutler.realestates.domain.entity.RealestateDocument;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CalculateColorProcessor {
    private final CalculateService calculateService;

    public String execute(Long realestateAsset, Long monthlyAvailableAsset, RealestateDocument doc, String realEstateTypeName,
        MemberCalCommand memberCommand) {
        if (realEstateTypeName.equals("매매")) {
            return calculateService.deal(realestateAsset, monthlyAvailableAsset, doc, memberCommand.getCreditRating(), memberCommand.getNumberOfHouses());
        } else if (realEstateTypeName.equals("전세")) {
            return calculateService.lease(realestateAsset, monthlyAvailableAsset, doc, memberCommand.getCreditRating(), memberCommand.getNumberOfHouses());
        } else if (realEstateTypeName.equals("월세")) {
            return calculateService.rent(realestateAsset, monthlyAvailableAsset, doc, memberCommand.getCreditRating(), memberCommand.getNumberOfHouses());
        } else return "yellow";
    }
}
