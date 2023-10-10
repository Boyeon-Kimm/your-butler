"use client";

import styled from "styled-components";
import Image from "next/image";

export const ContainerDiv = styled.main`
  margin: 0;

  ::selection {
    background-color: #afffe3;
  }
`;

export const UpperDiv = styled.div`
  height: 40rem;
  position: relative;
  -webkit-user-select: none;
`;

export const FirstP = styled.p`
  font-size: 2.2rem;
  font-weight: 600;
  margin-bottom: 1rem;
  letter-spacing: 0.1rem;
`;

export const SecondP = styled.p`
  font-size: 2.4rem;
  font-weight: 600;
  margin-bottom: 2rem;
  letter-spacing: 0.2rem;
`;

export const StyledInput = styled.input`
  padding: 1rem 1.5rem;
  width: 38rem;
  border-radius: 5rem;
  border: none;
  font-size: 1rem;
  transition: box-shadow 0.1s ease;

  &:focus {
    box-shadow: 0 0 1rem 0.4rem rgba(255, 255, 255);
    outline-color: transparent;
  }
`;

export const MainImg = styled(Image)`
  vertical-align: middle;
`;

export const PDiv = styled.div`
  text-align: center;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: white;
`;

export const InputDiv = styled.div`
  align-items: center;
  display: flex;
  flex-direction: column;
  height: 5rem;
`;

export const StyledSvg = styled.svg`
  height: 1.5rem;
  width: 1.5rem;
`;

export const MiddleDiv = styled.div`
  height: 30rem;
  background-color: #e8f9e9;
  display: flex;
  flex-direction: column;
  padding: 5rem 10rem;
`;

export const LowerDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  text-align: center;
  align-items: center;
  gap: 1rem;
  padding: 5rem 5rem 10rem 5rem;
`;

export const TitleP = styled.p`
  font-weight: 600;
  font-size: 2rem;
  color: #312d2d;
`;

export const ContentP = styled.p`
  font-size: 1.2rem;
  width: 30rem;
  line-height: 1.5rem;
  color: #5d5d5d;
`;

export const ContentDiv = styled.div`
  width: 80%;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  padding-top: 2rem;
`;

export const FirstDiv = styled.div`
  width: 20rem;
  height: 24rem;
  background-color: #e8f9e9;
  border-radius: 0.8rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 1rem;
`;

export const SecondDiv = styled.div`
  width: 20rem;
  height: 24rem;
  border: solid 2px #e8f9e9;
  border-radius: 0.8rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 1rem;
`;

export const CircleDiv = styled.div`
  height: 8rem;
  width: 8rem;
  border-radius: 10rem;
  box-shadow: 0 5px 5px 2px #a6baa7 inset;
  display: flex;
  justify-content: center;
  align-items: center;
`;

export const CircleSecondDiv = styled.div`
  height: 8rem;
  width: 8rem;
  border-radius: 10rem;
  box-shadow: 0 5px 5px 2px #a6baa7;
  display: flex;
  justify-content: center;
  align-items: center;
`;

export const AboutTitleP = styled.p`
  color: #312d2d;
  font-size: 1.2rem;
  margin-top: 1rem;
  font-weight: 500;
`;

export const AboutContentP = styled.p`
  width: 14rem;
  color: #5d5d5d;
  line-height: 1.3rem;
`;

export const AboutSvg = styled.svg`
  height: 4rem;
  width: 4rem;
  fill: #334835;
`;

export const ListContainerDiv = styled.div`
  height: 23rem;
  display: flex;
  position: relative;
  overflow: hidden;
  justify-content: space-around;
  align-items: center;
  background-color: white;
  padding: 1rem;
  border-radius: 0.4rem;
`;

export const ListAboutDiv = styled.div`
  display: flex;
  justify-content: space-between;
`;

export const NextSvg = styled.svg`
  cursor: pointer;
  fill: #3f3d3d;
`;

export const ViewMoreDiv = styled.div`
  display: flex;
  align-items: center;
  cursor: pointer;
`;

export const NextSvgDiv = styled.div`
  height: 100%;
  padding: 0.5rem;
  display: flex;
  align-items: center;
`;

export const ListDiv = styled.div`
  width: 95%;
  display: flex;
  gap: 1rem;
  overflow: hidden;
  flex-wrap: nowrap;
`;

export const SearchBtn = styled.button`
  background: none;
  border: none;
  position: relative;
  right: -18.7rem;
  top: -2.6rem;
  cursor: pointer;
  background-color: white;
`;
