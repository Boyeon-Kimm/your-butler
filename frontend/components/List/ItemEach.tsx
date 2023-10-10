"use client";

import React, { useState } from "react";
import styled from "styled-components";
import estate1 from "@/public/assets/defaultimg.png";
import ColorDot from "./ColorDot";
import colors from "@/constants/colors";

interface Item {
  realestateId: number;
  complexNo: number; // 주택번호: 1165010700
  complexName: string; // 주택이름: 아크로리버파크
  address: string; // 주소: 서울시 강남구 역삼동
  realEstateTypeName: string; // 방종류: 아파트
  tradeTypeName: string; // 거래방식: 매매
  floorInfo?: string; // 층수: 4/15
  supplyArea: number; // 공급면적: 112.74 (제곱미터)
  exclusiveArea: number; // 전용면적: 84.97 (제곱미터)
  maintenanceFee: string; // 관리비: 10만
  rentPrc: string; // 월세: 32만
  dealOrWarrantPrc: string; // 매매가 혹은 전월세 보증금: 3억 7000
  imageSrc?: string;
}

interface IProps {
  item?: Item;
  width?: string;
  height?: string;
  colordot?: string;
  holdingAsset?: number | null;
}

export default function ItemEach({ item, width, height, colordot, holdingAsset }: IProps) {

  return (
    <EstateDiv
      onClick={() => {
        window.location.href = `/detail/${item?.realestateId}?holdingAsset=${holdingAsset}`;
      }}
    >
      <ImageDiv>
        {item?.imageSrc.length>2 ?
        <EstateImage src={JSON.parse(item?.imageSrc.replace(/'/g, '"'))[0]?.imageSrc}/> :
        <EstateImage src={estate1.src}/>}
      </ImageDiv>
      <AboutEstateDiv>
        <RoomP>
          {item?.address} {item?.realEstateTypeName}
        </RoomP>
        <PriceDiv>
          <PriceP>
          {item?.realEstateTypeName} {item?.dealOrWarrantPrc} {(item?.realEstateTypeName !== "매매") && (item?.realEstateTypeName !== "전세") && <span>/{Math.floor(parseInt(item?.rentPrc))}</span>}
          </PriceP>
          <ColorDot color={colordot || "transparent"} />
        </PriceDiv>
        <DetailP>{item?.complexName}</DetailP>
        <DetailP>
          관리비 {parseInt(item?.maintenanceFee) / 10000}만
        </DetailP>
      </AboutEstateDiv>
    </EstateDiv>
  );
}

interface EstateDivProps {
  height?: string;
  width?: string;
}

const EstateDiv = styled.div<EstateDivProps>`
  height: ${(props) => props.height || "19rem"};
  width: ${(props) => props.width || "18rem"};
  padding: 0 1rem 1.8rem 1rem;
  border: solid 1px lightgray;
  border-radius: 1rem;
  position: relative;

  p {
    display: block;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }
`;

const ImageDiv = styled.div`
  width: 100%;
  height: 65%;
  overflow: hidden;
`;

const EstateImage = styled.img`
  object-fit: contain;
  width: 100%;
  height: 100%;
`;

const AboutEstateDiv = styled.div`
  line-height: 1.3rem;
  cursor: pointer;
`;

const DetailP = styled.p`
  font-size: 0.9rem;
  color: darkgray;
`;

const RoomP = styled.p`
  font-size: 0.8rem;
  color: gray;
  margin-bottom: 0.4rem;
`;

const PriceP = styled.p`
  font-size: 1.5rem;
  font-weight: 700;
`;

const HeartSvg = styled.svg`
  position: absolute;
  top: 30px;
  right: 30px;
  height: 1.5rem;
  width: 1.5rem;
  /* z-index: 9999; */
  cursor: pointer;
`;

const PriceDiv = styled.div`
  display: flex;
  flex-direction: row;
  gap: 0.7rem;
  align-items: center;
  padding-bottom: 0.5rem;
`;
