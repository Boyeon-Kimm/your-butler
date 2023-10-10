package com.ficrew.yourbutler.realestates.application.command;

import com.ficrew.yourbutler.realestates.domain.entity.RoomType;
import com.ficrew.yourbutler.realestates.domain.entity.TradeType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchCommand {

    private Integer size;
    private Integer from;
    private String textKeyword;
    // 매매가/전세가/월세 보증금  입력값
    private Long realestateAsset;
    // 월 여유자금 입력
    private Long monthlyAvailableAsset;
    private TradeType tradeType; // 거래 유형: 매매, 월세, 전세
    private List<RoomType> roomType; // 방종류: 아파트, 오피스텔, 단독다가구, 원룸, 빌라연립, 주택(상가, 한옥, ..)
    // 가격
    private Long dwMin; // 보증금/전세가 최소
    private Long dwMax;
    private Long dpMin; // 매매가 최소
    private Long dpMax;
    private Long rpMin; // 월세
    private Long rpMax;
    private Long mfMin; // 관리비
    private Long mfMax;
    private Long rsMin;
    private Long rsMax;
    private Integer uay; // 사용 승인일 기간 (다중택일)

}
