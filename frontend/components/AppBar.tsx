"use client";

import { signIn, signOut } from "next-auth/react";
import Link from "next/link";
import React, { useEffect } from "react";
import styled from "styled-components";
import favicon from "@/app/favicon.ico";
import greenlogo from "@/public/assets/greenlogo.png";
import whitelogo from "@/public/assets/whitelogo.png";
import Image from "next/image";
import { IBM_Plex_Sans_KR } from "@next/font/google";

const ibmPlexSansKR = IBM_Plex_Sans_KR({
  weight: ["300", "400", "500", "700"],
  subsets: ["latin"],
});

const AppBar = ({ backgroundColor, color, logo, user, logoLogout = false }) => {
  const logoSrc = logo === "greenlogo" ? "/assets/greenlogo.png" : "/assets/whitelogo.png";

  return (
    <ContainerDiv backgroundColor={backgroundColor}>
      {logoLogout ? (
        <Link href="/">
          <Logo onClick={() => signOut()}>
            <Image src={logoSrc} alt="Logo" width={180} height={55} />
          </Logo>
        </Link>
      ) : (
        <Link href="/">
          <Logo>
            <Image src={logoSrc} alt="Logo" width={180} height={55} />
          </Logo>
        </Link>
      )}
      <NavDiv className={ibmPlexSansKR.className}>
        <StyledLink href="/search" color={color}>
          매물검색
        </StyledLink>
        {user ? (
          <>
            <StyledLink href="/mypage" color={color}>
              마이페이지
            </StyledLink>
            <img
              src={user?.picture ? user?.picture : favicon.src}
              style={{ width: "30px", height: "30px", backgroundColor: "white" }}
            />
            <StyledUserName color={color}>{user?.nickname}</StyledUserName>
            <YellowBtn onClick={() => signOut()}>로그아웃</YellowBtn>
          </>
        ) : (
          <>
            <YellowBtn onClick={() => signIn()}>로그인</YellowBtn>
          </>
        )}
      </NavDiv>
    </ContainerDiv>
  );
};

interface BgProp {
  backgroundColor?: string;
}

interface ColorProp {
  color?: string;
}

const ContainerDiv = styled.div<BgProp>`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem 4rem;
  background-color: ${(props) => props.backgroundColor || "#334835"};

  ::selection {
    background-color: #afffe3;
  }
`;

const Logo = styled.div`
  height: 3rem;
  width: 10rem;
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const LogoutLogo = styled.div<ColorProp>`
  font-size: 1.5rem;
  color: ${(props) => props.color || "white"};
  text-decoration-line: none;
`;
/* ${({ theme }) => theme.logoFont || ""}; */

const StyledLink = styled(Link)<ColorProp>`
  color: ${(props) => props.color || "white"};
  text-decoration-line: none;
`;

const StyledUserName = styled.p<ColorProp>`
  color: ${(props) => props.color || "white"};
  text-decoration-line: none;
`;

const YellowBtn = styled.button`
  background-color: #ffc436;
  border: solid 2px #ffc436;
  padding: 0.4rem 0.7rem;
  font-weight: 500;
  border-radius: 0.2rem;
  cursor: pointer;

  &:hover {
    background-color: transparent;
    color: #ffc436;
  }
`;

const NavDiv = styled.div`
  /* width: 24rem; */
  display: flex;
  justify-content: space-between;
  gap: 3rem;
  align-items: center;
`;

export default AppBar;
