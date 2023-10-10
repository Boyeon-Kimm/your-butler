"use client";

import React from "react";
import Footer from "@/components/Footer";
import AppBar from "@/components/AppBar";
import styled from "styled-components";
import { IBM_Plex_Sans_KR } from "@next/font/google";
import colors from "@/constants/colors";
import butler from "@/public/assets/butler.png";
import Image from "next/image";
import Button from "@/components/Button/Button";
import Link from "next/link";

const ibmPlexSansKR = IBM_Plex_Sans_KR({
  weight: ["300", "400", "500", "700"],
  subsets: ["latin"],
});

const NotFound = () => {
  return (
    <Container className={ibmPlexSansKR.className}>
      {/* <AppBar backgroundColor={colors.darkgreen} logo="whitelogo" color="white" /> */}
      <MiddleDiv>
        <ImgDiv>
          <Image src={butler} alt="butler" height={150} width={150} />
        </ImgDiv>
        <TitleDiv>
          <BoldP>죄송합니다.</BoldP>
          <BoldP>요청하신 페이지를 찾을 수 없습니다.</BoldP>
        </TitleDiv>
        <TitleDiv>
          <p>방문하시려는 페이지의 주소가 잘못 입력되었거나,</p>
          <p>페이지의 주소가 변경 혹은 삭제되어 요청하신 페이지를 찾을 수 없습니다.</p>
          <p>입력하신 주소가 정확한지 다시 한 번 확인해 주시기 바랍니다.</p>
        </TitleDiv>
        <ButtonDiv href="/">
          <Button Variant="yellowFilled" Kind="small" Rounded="circle">
            메인으로 이동하기
          </Button>
        </ButtonDiv>
      </MiddleDiv>
      <Footer />
    </Container>
  );
};

const Container = styled.div``;

const MiddleDiv = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 6rem 0;
`;

const ImgDiv = styled.div`
  height: 10rem;
  width: 10rem;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const TitleDiv = styled.div`
  text-align: center;
  gap: 0.6rem;
  display: flex;
  flex-direction: column;
  padding: 1rem 0;
`;

const BoldP = styled.p`
  font-weight: 600;
  font-size: 1.4rem;
`;

const ButtonDiv = styled(Link)`
  margin-top: 1rem;
`;

export default NotFound;
