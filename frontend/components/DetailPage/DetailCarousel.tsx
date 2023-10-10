"use client";

import { useState,useEffect } from "react";
import Img1 from "@/public/assets/estate1.png";
import {
  TopDiv,
  NextSvgDiv,
  NextSvg,
  ImgDiv,
  StyledImg,
  Popup,
  CloseButton,
  PopupImage,
} from "@/components/DetailPage/DetailPage";

const DetailCarousel = ({imgList}) => {
  // 사진 캐러셀 기능
  const [currentIdx, setCurrentIdx] = useState(0);

  const images = [Img1, Img1, Img1, Img1, Img1];

  const prevImage = () => {
    setCurrentIdx((prevIdx) => (prevIdx === 0 ? images.length - 1 : prevIdx - 1));
  };

  const nextImage = () => {
    setCurrentIdx((prevIdx) => (prevIdx === images.length - 1 ? 0 : prevIdx + 1));
  };

  // 사진 눌렀을 때 사진 원본 보여주는 모달창
  const [popupVisible, setPopupVisible] = useState(false);
  const [clickedImageIndex, setClickedImageIndex] = useState(0);

  const openPopup = (index) => {
    setClickedImageIndex(index);
    setPopupVisible(true);
  };

  const closePopup = () => {
    setPopupVisible(false);
  };

  return (
    <TopDiv>
      <NextSvgDiv className="prev" onClick={prevImage}>
        <NextSvg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 320 512">
          <path d="M9.4 233.4c-12.5 12.5-12.5 32.8 0 45.3l192 192c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3L77.3 256 246.6 86.6c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0l-192 192z" />
        </NextSvg>
      </NextSvgDiv>
      <ImgDiv>
        {imgList && JSON.parse(imgList.replace(/'/g, '"')).map((image, index) => (
          <StyledImg
            key={index}
            src={image?.imageSrc}
            alt="estate"
            onClick={() => openPopup(index)}
            style={{
              transform: `translateX(-${currentIdx * 100}%)`,
              cursor: "pointer",
            }}
          />
        ))}
      </ImgDiv>
      {popupVisible && (
        <Popup>
          <CloseButton
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            strokeWidth="1.5"
            stroke="currentColor"
            className="w-6 h-6"
            onClick={closePopup}
          >
            <path strokeLinecap="round" strokeLinejoin="round" d="M6 18L18 6M6 6l12 12" />
          </CloseButton>
          {imgList && <PopupImage src={JSON.parse(imgList.replace(/'/g, '"'))[clickedImageIndex].imageSrc} alt="estate" />}
        </Popup>
      )}
      <NextSvgDiv className="next" onClick={nextImage}>
        <NextSvg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 320 512">
          <path d="M310.6 233.4c12.5 12.5 12.5 32.8 0 45.3l-192 192c-12.5 12.5-32.8 12.5-45.3 0s-12.5-32.8 0-45.3L242.7 256 73.4 86.6c-12.5-12.5-12.5-32.8 0-45.3s32.8-12.5 45.3 0l192 192z" />
        </NextSvg>
      </NextSvgDiv>
    </TopDiv>
  );
};

export default DetailCarousel;
