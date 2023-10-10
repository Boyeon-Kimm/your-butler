import React, { Component, useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import Slider from "@material-ui/core/Slider";
import styled from "styled-components";

const useStyles = makeStyles({
  root: {
    width: 285,
  },
});

export default function RangeSlider({ title, unit, minValue, maxValue, change }) {
  const classes = useStyles();
  const [value, setValue] = useState([minValue, maxValue]); // 값

  useEffect(() => {
    //set작은값(value주에 작은거)
    //set큰값(value중에 큰거)
    change(value[0], value[1]);
  }, [value]);

  //천단위 , 찍기 위한 함수
  const numberFormat = (num) => {
    if (num > 1000) {
      return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    } else {
      return num;
    }
  };

  // 슬라이더를 변화시킬 때 마다 value 값 조정
  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <div className={classes.root} style={{ marginTop: "2.5rem" }}>
      <TitleDiv>
        <SubtitleP>{title}</SubtitleP>
        <ToP>
          {numberFormat(value[0])} ~ {numberFormat(value[1])}
          {unit && ` ${unit}`}
        </ToP>
      </TitleDiv>
      <Slider
        value={value} // 가격 슬라이더의 값
        // defaultValue={[minValue, maxValue]} // 가격 슬라이더 최초 범위
        onChange={handleChange} // 슬라이더가 변할 때마다 value값을 조정하는 함수
        valueLabelDisplay="auto"
        aria-labelledby="range-slider" // 슬라이더 형태
        // getAriaValueText={valuetext}
        min={minValue}
        max={maxValue}
      />
    </div>
  );
}

const TitleDiv = styled.div`
  display: flex;
  justify-content: space-between;
  font-size: 0.9rem;
  margin: 1rem 0;
`;

const SubtitleP = styled.p`
  font-weight: 500;
  color: gray;
`;

const ToP = styled.p`
  font-weight: 500;
  color: #3f51b5;
`;
