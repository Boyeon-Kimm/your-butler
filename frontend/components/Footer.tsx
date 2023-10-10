"use client";

import styled from "styled-components";
import { IBM_Plex_Sans_KR } from "@next/font/google";
import Link from "next/link";

const ibmPlexSansKR = IBM_Plex_Sans_KR({
  weight: ["300", "400", "500", "700"],
  subsets: ["latin"],
});

export default function Footer() {
  return (
    <Container className={ibmPlexSansKR.className}>
      <LeftDiv>
        <StyledLink href="/">사이트소개</StyledLink>
        <StyledLink href="/">이용약관</StyledLink>
        <StyledLink href="/">개인정보처리방침</StyledLink>
      </LeftDiv>
      <RightDiv>
        <p>ⓒ 당신의집사</p>
      </RightDiv>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  justify-content: space-between;
  color: gray;
  padding: 3rem 9rem;

  ::selection {
    background-color: #afffe3;
  }
`;

const RightDiv = styled.div`
  font-weight: 600;
  font-size: 1.2rem;
  color: lightgray;
`;

const LeftDiv = styled.div`
  display: flex;
  gap: 2rem;
  font-size: 0.9rem;
`;

const StyledLink = styled(Link)`
  cursor: pointer;
  color: gray;
  text-decoration-line: none;
`;
