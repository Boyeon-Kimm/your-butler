package com.ficrew.yourbutler.realestates.infrastructure.serviceImpl;

import static com.ficrew.yourbutler.realestates.infrastructure.util.CreditUtils.getInterestRate;

import com.ficrew.yourbutler.member.domain.entity.NumberOfHouses;
import com.ficrew.yourbutler.realestates.domain.service.CalculateService;
import com.ficrew.yourbutler.realestates.domain.entity.RealestateDocument;
import org.springframework.stereotype.Component;

@Component
public class CalculateServiceImpl implements CalculateService {

    /*
        매매: 주택담보대출
        현재 자산 >= 매매가: 초록
        현재 자산 < 매매가
        : 1) ltv 적용 가격(대출최대가능금액) + 현재 자산 >= 매매가: 노랑
        : 2) 반대: 빨강
     */
    @Override
    public String deal(Long realestateAsset, Long monthlyAvailableAsset, RealestateDocument doc,
        Integer creditRating, NumberOfHouses numberOfHouses) {
        Double ltvPrice = getLtvPrice(numberOfHouses, doc.getDealOrWarrantPrc_numeric());
        Long dealPrice = doc.getDealOrWarrantPrc_numeric();
        if (realestateAsset >= dealPrice) {
            return "green";
        } else {
            if (ltvPrice + realestateAsset >= dealPrice) {
                return "yellow";
            } else {
                return "red";
            }
        }
    }

    private Double getLtvPrice(NumberOfHouses numberOfHouses, Long dealOrWarrantPrcNumeric) {
        if (numberOfHouses.equals(NumberOfHouses.NONE)) {
            return dealOrWarrantPrcNumeric * 0.5;
        } else {
            return dealOrWarrantPrcNumeric * 0.3;
        }
    }

    /*
        전세: 전세자금대출
        1) 현자산 >= 보증금: 초록
        2) 현 자산이 전세보증금의 30% 이상에 해당하면 노랑, 그 이하면 빨강
     */
    @Override
    public String lease(Long realestateAsset, Long monthlyAvailableAsset, RealestateDocument doc,
        Integer creditRating, NumberOfHouses numberOfHouses) {
        Long leasePrice = doc.getDealOrWarrantPrc_numeric();
        if (realestateAsset >= leasePrice) {
            return "green";
        } else {
            if (leasePrice * 0.3 <= realestateAsset) {
                return "yellow";
            } else {
                return "red";
            }
        }
    }

    /*
        월세: 신용도 기반 신용대출
        (신용도 기반으로 거의 대출 가능하기 때문에, 대출 가능 여부 및 대출 했을 때 부동산 거래 가능 여부를 보기 보다는 월에 감당 가능한 금액인지 아닌지 체크)

        월 여유자금: 월세 포함 이자까지 감당 가능한 금액
        현재 자산 >= 보증금: 초록
        현재 자산 < 보증금 &
        1) 현재자산과 보증금 차이 × 신용도 기반 이자율 + 월세 <= 월 여유자금: 노랑
        2) > 이면 빨강

        : [로그인 사용자: 신용도 기반 이자율 / 비로그인 사용자: 평균 이자율]

     */
    @Override
    public String rent(Long realestateAsset, Long monthlyAvailableAsset, RealestateDocument doc,
        Integer creditRating, NumberOfHouses numberOfHouses) {
        Long rentPrice = doc.getRentPrc_numeric() * 10000;
        long diff = realestateAsset - rentPrice;
        if (diff >= 0) {
            return "green";
        } else {
            if (diff * getInterestRate(creditRating) + rentPrice <= monthlyAvailableAsset) {
                return "yellow";
            } else {
                return "red";
            }
        }
    }
}
