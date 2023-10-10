"use client";

import AppBar from "@/components/AppBar";
import Footer from "@/components/Footer";
import styled from "styled-components";
import colors from "@/constants/colors";
import React from "react";
import { useState, useEffect } from "react";
import { useSession } from "next-auth/react";
import authApi from "@/api/authApi";
import { IBM_Plex_Sans_KR } from "@next/font/google";

const ibmPlexSansKR = IBM_Plex_Sans_KR({
  weight: ["300", "400", "500", "700"],
  subsets: ["latin"],
});

const Modify = () => {
  const { data: session, status } = useSession();
  const [user, setUser] = useState(null);
  const [age, setAge] = useState<number | null>();
  const [houses, setHouses] = useState<string>("none");
  const [budget, setBudget] = useState<number | null>();
  const [jasan, setJasan] = useState<number | null>();
  const [credit, setCredit] = useState<number | null>();

  useEffect(() => {
    if (session) {
      //@ts-ignore
      configureUser(session?.userData.token, session?.userData.socialType);
    }
  }, [session]);

  useEffect(() => {
    if (status === "unauthenticated") {
      alert("잘못된 접근입니다.");
      window.location.href = "/";
    }
  }, [status]);

  const configureUser = async (token: String, provider: String) => {
    try {
      let res = await authApi.getUser(token, provider);
      if (res.status === 200) {
        setUser((prev) => {
          setAge(res.data.memberResponse.age);
          setHouses(res.data.memberResponse.numberOfHouses);
          setBudget(res.data.memberResponse.holdingAsset);
          setJasan(res.data.memberResponse.monthlyAvailableAsset);
          setCredit(res.data.memberResponse.creditRating);
          return res.data.memberResponse;
        });
      } else {
        alert("비정상적인 접근입니다.");
        window.location.href = "/";
      }
    } catch {
      alert("비정상적인 접근입니다.");
      window.location.href = "/";
    }
  };

  const handleAge = (e: React.ChangeEvent<HTMLInputElement>) => {
    setAge(parseInt(e.target.value));
  };

  const handleHouses = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setHouses(e.target.value);
  };

  const handleBudget = (e: React.ChangeEvent<HTMLInputElement>) => {
    setBudget(parseInt(e.target.value));
  };

  const handleJasan = (e: React.ChangeEvent<HTMLInputElement>) => {
    setJasan(parseInt(e.target.value));
  };

  const handleCredit = (e: React.ChangeEvent<HTMLInputElement>) => {
    setCredit(parseInt(e.target.value));
  };

  const modifyUser = async (userData: any | null) => {
    if (!age || !houses || !budget) {
      alert("나이, 주택 수, 예산은 필수입니다.");
      return;
    }
    let res = await authApi.modify(userData, age, houses, budget, jasan, credit);
    console.log(res);
    if (res.status === 200) {
      alert("수정 완료");
      window.location.href = "/";
    }
  };

  const goHome = () => {
    let flag = window.confirm("수정을 중단하고 메인 화면으로 가시겠습니까?");
    if (flag) {
      window.location.href = "/";
    }
  };

  return (
    <Container>
      <AppBar backgroundColor="transparent" logo="greenlogo" color="#334835" user={user} />
      <CenterDiv className={ibmPlexSansKR.className}>
        <MiddleDiv>
          <TitleDiv>
            <TitleP>추가 정보 수정</TitleP>
            <SubDiv>
              <SubP>더 정확한 추천을 위해 최대한 많은 정보를 입력해주세요 :)</SubP>
            </SubDiv>
          </TitleDiv>
          <InputDiv>
            <InputTitleP>필수</InputTitleP>
            <StyledInput
              type="number"
              placeholder="나이"
              defaultValue={age}
              onChange={(e) => {
                handleAge(e);
              }}
              required
            />
            <StyledSelect
              required
              defaultValue={houses}
              onChange={(e) => {
                handleHouses(e);
              }}
            >
              <option value="">-- 주택 수를 선택하세요 --</option>
              <option value="none">무주택</option>
              <option value="one">1주택</option>
              <option value="two">2주택</option>
              <option value="more_than_two">3주택 이상</option>
            </StyledSelect>
            <StyledInput
              type="number"
              required
              placeholder="부동산 거래 예산"
              defaultValue={budget}
              onChange={(e) => {
                handleBudget(e);
              }}
            />
            <SubTitleP>
              <InputTitleP>선택</InputTitleP>
              <GrayP>(월세 매물 추천 시 활용됩니다.)</GrayP>
            </SubTitleP>
            <StyledInput
              type="number"
              placeholder="월 가용자산"
              defaultValue={jasan}
              onChange={(e) => {
                handleJasan(e);
              }}
            />
            <StyledInput
              type="number"
              placeholder="신용도"
              defaultValue={credit}
              onChange={(e) => {
                handleCredit(e);
              }}
            />
          </InputDiv>
          <YellowBtn
            onClick={() => {
              // @ts-ignore
              modifyUser(session?.userData);
            }}
          >
            수정
          </YellowBtn>
          <YellowBtn onClick={goHome}>취소</YellowBtn>
        </MiddleDiv>
      </CenterDiv>
      <Footer />
    </Container>
  );
};

const Container = styled.div`
  display: flex;
  justify-content: center;
  flex-direction: column;
`;

const MiddleDiv = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 3rem;
  margin: 3rem;
  width: 30rem;
  border: solid 1px lightgray;
`;

const CenterDiv = styled.div`
  display: flex;
  justify-content: center;
`;

const TitleDiv = styled.div`
  text-align: center;
  gap: 1rem;
`;

const TitleP = styled.p`
  font-size: 1.8rem;
  font-weight: 600;
`;

const SubP = styled.p`
  line-height: 1.3rem;
  color: gray;
  font-size: 0.9rem;
  font-weight: 600;
`;

const SubDiv = styled.div`
  margin: 0.8rem 0 2rem 0;
`;

const InputDiv = styled.div`
  display: flex;
  flex-direction: column;
  width: 20rem;
  gap: 0.5rem;
  margin-bottom: 1rem;
`;

const InputTitleP = styled.p`
  font-size: 0.9rem;
  font-weight: 600;
  color: ${colors.darkgray};
`;

const StyledInput = styled.input`
  height: 2rem;
  padding: 0.3rem 0.7rem;
  border: solid 1px #f1f1f1;
  background-color: #f1f1f1;
  border-radius: 0.4rem;

  &:focus {
    box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
    outline-color: #f1f1f1;
  }

  &::-webkit-outer-spin-button,
  &::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
  }
`;

const StyledSelect = styled.select`
  height: 2.7rem;
  padding: 0.3rem 0.5rem;
  border: solid 1px #f1f1f1;
  background-color: #f1f1f1;
  border-radius: 0.4rem;
`;

const SubTitleP = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 0.5rem;
  margin-top: 1rem;
`;

const GrayP = styled.p`
  font-size: 0.8rem;
  color: gray;
`;

const YellowBtn = styled.button`
  background-color: ${colors.yellow};
  border: solid 2px ${colors.yellow};
  padding: 0.7rem 0;
  width: 20rem;
  border-radius: 0.4rem;
  margin-top: 0.3rem;
  font-weight: 600;
  cursor: pointer;

  &:hover {
    background-color: transparent;
  }
`;

export default Modify;
