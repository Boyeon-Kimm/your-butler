"use client";

import styled from "styled-components";

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  padding: 3rem;
  -webkit-font-smoothing: antialiased;

  ::selection {
    background-color: #afffe3;
  }
`;

export const TopDiv = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`;

export const ImgDiv = styled.div`
  display: flex;
  width: 88%;
  gap: 1rem;
  overflow-x: hidden;
  transition: transform 0.3s ease;
  -webkit-user-select: none;
`;

export const BottomDiv = styled.div`
  display: flex;
  padding: 1rem 5rem;
`;

export const LeftDiv = styled.div`
  width: 50%;
`;

export const RightDiv = styled.div`
  width: 50%;
`;

export const StyledImg = styled.img`
  height: 19rem;
  width: 19rem;
  border-radius: 1rem;
  object-fit: cover;
`;

export const TitleP = styled.p`
  font-size: 1.3rem;
  font-weight: 600;
`;

export const ChatMiddleDiv = styled.div<AskPprops>`
  height: 75%;
  padding: 0.5rem 0;
  display: ${(props) => (props.isVisible ? "block" : "none")};
  flex-direction: column;
  overflow-y: scroll;

  &::-webkit-scrollbar {
    width: 8px;
    background-color: #e8e2d9;
  }

  &::-webkit-scrollbar-thumb {
    background-color: #acb4a8;
    border-radius: 3px;
  }

  &::-webkit-scrollbar-thumb:hover {
    background-color: #818a7e;
  }

  &::-webkit-scrollbar-thumb:active {
    background-color: #656c62;
  }
`;

export const ChatBottomDiv = styled.div<AskPprops>`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  display: ${(props) => (props.isVisible ? "block" : "none")};
`;

export const MessageInput = styled.input`
  padding: 0.2rem 0.6rem;
  height: 1.7rem;
  width: 85%;
  border: solid 1px #f1f1f1;
  background-color: #f1f1f1;
  border-radius: 0.4rem;
  /* float: left; */

  &:focus {
    box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
    outline-color: #f1f1f1;
  }
`;

export const ContainerP = styled.div``;

export const SubP = styled.p`
  color: gray;
  font-weight: 600;
  margin-top: 0.5rem;
`;

export const AboutDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  margin-top: 2rem;
`;

export const AboutP = styled.p`
  font-weight: 600;
  margin-top: 2rem;
  margin-bottom: 1.2rem;
  font-size: 1.1rem;
`;

export const AboutEachDiv = styled.div`
  display: flex;
  flex-direction: column;
  gap: 1rem;
`;

export const AboutTitleP = styled.p`
  font-weight: 600;
  color: gray;
`;

export const AboutDetailDiv = styled.div`
  display: flex;
  justify-content: space-between;
  width: 85%;
  border-bottom: solid 1px lightgray;
  padding: 0.7rem 0;
`;

export const PriceP = styled.p`
  font-size: 1.7rem;
  font-weight: 600;
`;

export const InfoDiv = styled.div`
  display: flex;
  justify-content: center;
  position: sticky;
  height: 16rem;
  border-radius: 1rem;
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
`;

export const BlueP = styled.p`
  color: #436fe9;
  font-weight: 600;
  font-size: 1.8rem;
`;

export const InfoDetailDiv = styled.div`
  width: 50%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 0.8rem;
`;

export const LightP = styled.p`
  color: lightgray;
  font-weight: 600;
  font-size: 0.8rem;
`;

export const LightPDiv = styled.div`
  text-align: center;
  line-height: 1.2rem;
`;

export const LoadingP = styled.p`
  padding: 0.5rem;
  text-align: center;
  color: gray;
  font-weight: 600;
`;

export const LoadingDiv = styled.div`
  height: 2rem;
`;

export const LineHr = styled.hr`
  border: solid 1px #f6f6f6;
`;

export const ChatBotDiv = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 3.8rem;
  width: 3.8rem;
  border-radius: 5rem;
  background-color: #ffc436;
  cursor: pointer;
  box-shadow: rgba(27, 15, 3, 0.25) 0px 50px 100px -20px, rgba(0, 0, 0, 0.3) 0px 30px 60px -30px,
    rgba(23, 17, 2, 0.35) 0px -2px 6px 0px inset;
`;

export const ChatDiv = styled.div`
  display: flex;
  flex-direction: column;
  padding: 3rem;
  gap: 0.6rem;
  position: sticky;
  top: 1px;
`;

interface AskPprops {
  children: any;
  isVisible: Boolean;
}

export const AskP = styled.p<AskPprops>`
  font-weight: 600;
  font-size: 1.1rem;
  padding: 0.3rem;
  padding-bottom: ${(props) => (props.isVisible ? "1rem" : "none")};
  border-bottom: ${(props) => (props.isVisible ? "solid 1px lightgray" : "none")};
`;

export const AskDiv = styled.div<{ expanded: Boolean }>`
  box-shadow: rgba(50, 50, 93, 0.25) 0px 50px 100px -20px, rgba(0, 0, 0, 0.3) 0px 30px 60px -30px,
    rgba(10, 37, 64, 0.35) 0px -2px 6px 0px inset;
  border-radius: 0 2rem 2rem 2rem;
  padding: 1.2rem;
  width: ${(props) => (props.expanded ? "24rem" : "12rem")};
  height: ${(props) => (props.expanded ? "33rem" : "auto")};
  transition: all 0.3s ease-in-out;
  margin-left: 3rem;
`;

export const NextSvgDiv = styled.div`
  cursor: pointer;
  position: absolute;
  top: 40%;
  transform: translateY(-50%);
  width: 30px;
  height: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: rgba(255, 255, 255, 0.5);
  border-radius: 50%;
  transition: background-color 0.3s ease;
  z-index: 2;

  &:hover {
    background-color: rgba(255, 255, 255, 0.8);
  }

  &.prev {
    left: 10px; // 왼쪽 버튼 위치 조정
  }

  &.next {
    right: 10px; // 오른쪽 버튼 위치 조정
  }
`;

export const NextSvg = styled.svg`
  fill: #333;
`;

export const Popup = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.8);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
`;

export const CloseButton = styled.svg`
  position: absolute;
  top: 30px;
  right: 30px;
  background: transparent;
  border: none;
  color: white;
  font-size: 20px;
  cursor: pointer;
  width: 2.5rem;
  height: 2.5rem;
`;

export const PopupImage = styled.img`
  max-width: 90%;
  max-height: 90%;
`;

export const StyledSvg = styled.svg`
  height: 1.3rem;
  width: 1.3rem;
  cursor: pointer;
`;

export const SendSvg = styled.svg`
  height: 1.3rem;
  width: 1.3rem;
  fill: #ffc436;
  align-items: center;
  display: flex;
`;

export const SvgBtn = styled.button`
  background-color: transparent;
  border: transparent;
  cursor: pointer;
  align-items: center;
`;

export const AboutInfoDiv = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
`;

export const LikeButton = styled.button`
  float: right;
  margin-right: 5.1rem;
  height: 2.5rem;
  width: 2.5rem;
  background-color: white;
  border: solid 2px #d3d2d2;
  border-radius: 2rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  cursor: pointer;
`;

export const TitleLikeDiv = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
`;
