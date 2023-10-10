"use client";

import styled from "styled-components";
import colors from "@/constants/colors";
import { useState } from "react";

const ChatTitleDiv = ({ title, isSelected, onClick }) => {
  const TitleNameDiv = styled.div`
    background-color: ${isSelected ? "#E8F9E9" : "white"};
    align-items: center;
    text-align: center;
    padding: 1.7rem 1rem;
    font-weight: ${isSelected ? "600" : null};
    color: ${colors.darkgray};
    border-bottom: solid 1px ${colors.lightgray};
  `;

  return (
    <TitleNameDiv onClick={onClick}>
      <TitleP>{title}</TitleP>
    </TitleNameDiv>
  );
};

const TitleP = styled.p`
  display: block;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  cursor: pointer;
`;

export default ChatTitleDiv;
