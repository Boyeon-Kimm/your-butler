"use client";

import Image from "next/image";
import AppBar from "@/components/AppBar";
import Footer from "@/components/Footer";
import { IBM_Plex_Sans_KR } from "next/font/google";
import DetailCarousel from "@/components/DetailPage/DetailCarousel";
import butler from "@/public/assets/butler.png";
import { useState } from "react";
import Chatting from "@/components/Chat/Chatting";
import {
  TitleP,
  AboutTitleP,
  Container,
  BottomDiv,
  LeftDiv,
  ContainerP,
  SubP,
  AboutDiv,
  AboutP,
  AboutEachDiv,
  AboutDetailDiv,
  RightDiv,
  InfoDiv,
  InfoDetailDiv,
  BlueP,
  LineHr,
  LightPDiv,
  LightP,
  ChatDiv,
  ChatBotDiv,
  AskDiv,
  AskP,
  ChatMiddleDiv,
  ChatBottomDiv,
  MessageInput,
  SvgBtn,
  SendSvg,
  LikeButton,
  TitleLikeDiv,
  LoadingP,
  LoadingDiv,
} from "@/components/DetailPage/DetailPage";
import { useSession } from "next-auth/react";
import { useEffect } from "react";
import authApi from "@/api/authApi";
import realEstateApi from "@/api/realEstateApi";
import chatApi from "@/api/chatApi";
import MoneyFormatter from "@/components/Input/MoneyFormatter";
import { useSearchParams } from "next/navigation";

function calculateInterestRate(houseType: string, userCreditRating: number): number {
  if (houseType === "매매") {
    return 4.77;
  } else if (houseType === "전세") {
    return 5.3;
  } else if (houseType === "월세") {
    if (userCreditRating >= 900) {
      return 8.69;
    } else if (userCreditRating >= 800) {
      return 9.33;
    } else if (userCreditRating >= 700) {
      return 10.2;
    } else if (userCreditRating >= 600) {
      return 11.21;
    } else if (userCreditRating >= 500) {
      return 12.49;
    } else if (userCreditRating >= 400) {
      return 11.43;
    } else if (userCreditRating >= 300) {
      return 7.7;
    } else {
      return 9.68;
    }
  }
  return 0; // Default value
}

const ibmPlexSansKR = IBM_Plex_Sans_KR({
  weight: ["300", "400", "500", "700"],
  subsets: ["latin"],
});

const DetailWithID = ({ params }) => {
  const { data: session, status } = useSession();
  const [user, setUser] = useState(null);
  const [house, setHouse] = useState<any | null>(null);
  const [msg, setMsg] = useState<String>("로그인이 필요합니다.");
  const [chatNo, setChatNo] = useState<number>(-1);
  const [chatList, setChatList] = useState([]);
  const [chatMsg, setChatMsg] = useState(null);
  const [loading, setLoading] = useState(false);

  const queryParam = useSearchParams();
  const holdingAsset = queryParam.get("holdingAsset");

  const [like, setLike] = useState<boolean>(false);

  const handleLike = async () => {
    let res = null;
    if (like) {
      // @ts-ignore
      res = await realEstateApi.unCheck(session?.userData, params.id);
    } else {
      // @ts-ignore
      res = await realEstateApi.check(session?.userData, params.id);
    }
    if (!res?.data) return;
    setLike(res.data.checked);
  };

 
  useEffect(() => {
    if (session) {
      // @ts-ignore
      configureUser(session?.userData.token, session?.userData.socialType);
    }
  }, [session]);

  useEffect(() => {
    if (params.id) {
      getHouse(params.id);
    }

    // @ts-ignore
    if (params.id && session?.userData) {
      // @ts-ignore
      getChat(session?.userData, params.id);
    }
  }, [params, session]);

  const getHouse = async (realestateId: number) => {
    try {
      // @ts-ignore
      let res = await realEstateApi.detailSearch(session?.userData, realestateId);
      setHouse(res.data);
      setLike(res.data.bookmark.checked);
    } catch {
      window.alert("존재하는 매물이 아니거나 오류가 발생했습니다.");
      window.location.href = "/";
    }
  };

  const getChat = async (userData: any | null, realestateId: number) => {
    let res = await chatApi.getChat(userData, realestateId);
    setChatList(res.data.messageList);
    setChatNo(res.data.chatRoomNumber);
    // setHouse(res.data);
  };

  // 챗봇 open
  const [isChatOpen, setIsChatOpen] = useState<Boolean>(false);

  const handleChatClick = () => {
    if (!user) {
      return;
    } else {
      setIsChatOpen(!isChatOpen);
    }
  };

  const configureUser = async (token: String, provider: String) => {
    try {
      let res = await authApi.getUser(token, provider);
      if (res.status === 200) {
        setUser(res.data.memberResponse);
        setMsg("집사에게 물어보세요!");
      } else {
        setUser(null);
        setMsg("로그인이 필요한 서비스입니다.");
      }
    } catch {
      setUser(null);
      setMsg("로그인이 필요한 서비스입니다.");
    }
  };

  const sendChat = async () => {
    if (!chatMsg) return;
    setLoading(true);
    try {
      // @ts-ignore
      let res = await chatApi.sendChat(session?.userData, user, house, chatMsg, chatNo, holdingAsset);
      if (res.status === 200) {
        setChatNo(res.data.chatRoomNumber);
        setChatList((prev) => {
          return [
            ...prev,
            {
              isBot: false,
              timeStamp: new Date(),
              message: chatMsg,
              chatRoomNumber: res.data.chatRoomNumber,
              loan: null,
            },
            { ...res.data },
          ];
        });
      }
      setChatMsg("");
    } catch {
      setChatList((prev) => {
        return [
          ...prev,
          { isBot: true, timeStamp: new Date(), message: "그런 어려운 말은 몰라용.", loan: null },
        ];
      });
    } finally {
      setChatMsg("");
      setLoading(false);
    }
  };

  //천단위 , 찍기 위한 함수
  const numberFormat = (num) => {
    if (num > 1000) {
      return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    } else {
      return "0";
    }
  };

  return (
    <main className={ibmPlexSansKR.className}>
      <AppBar backgroundColor="transparent" logo="greenlogo" color="#334835" user={user} />
      <Container className={ibmPlexSansKR.className}>
        <DetailCarousel imgList={house?.imageList ? house?.imageList : null} />
        <BottomDiv className={ibmPlexSansKR.className}>
          <LeftDiv>
            <TitleLikeDiv>
              <ContainerP>
                <TitleP>
                  {house?.complexName} · {house?.floorInfo.split("/")[0]}층
                </TitleP>
                <SubP>
                  {house?.address} {house?.complexName} {house?.buildingName}
                </SubP>
              </ContainerP>
              <div>
                <LikeButton onClick={handleLike}>
                  {!like ? (
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      fill="none"
                      viewBox="0 0 24 24"
                      strokeWidth="1.5"
                      stroke="red"
                      className="w-6 h-6"
                    >
                      <path
                        strokeLinecap="round"
                        strokeLinejoin="round"
                        d="M21 8.25c0-2.485-2.099-4.5-4.688-4.5-1.935 0-3.597 1.126-4.312 2.733-.715-1.607-2.377-2.733-4.313-2.733C5.1 3.75 3 5.765 3 8.25c0 7.22 9 12 9 12s9-4.78 9-12z"
                      />
                    </svg>
                  ) : (
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      fill="red"
                      viewBox="0 0 24 24"
                      strokeWidth="1.5"
                      stroke="red"
                      className="w-6 h-6"
                    >
                      <path
                        strokeLinecap="round"
                        strokeLinejoin="round"
                        d="M21 8.25c0-2.485-2.099-4.5-4.688-4.5-1.935 0-3.597 1.126-4.312 2.733-.715-1.607-2.377-2.733-4.313-2.733C5.1 3.75 3 5.765 3 8.25c0 7.22 9 12 9 12s9-4.78 9-12z"
                      />
                    </svg>
                  )}
                </LikeButton>
              </div>
            </TitleLikeDiv>
            <AboutDiv>
              <AboutP>가격정보</AboutP>
              <AboutEachDiv>
                <AboutDetailDiv>
                  <AboutTitleP>{house?.realEstateTypeName}</AboutTitleP>
                  <p>
                    {house?.dealOrWarrantPrc}{" "}
                    {house?.realEstateTypeName !== "매매" && (
                      <span>/ {house?.rentPrcNumeric}만원</span>
                    )}
                  </p>
                </AboutDetailDiv>
                <AboutDetailDiv>
                  <AboutTitleP>관리비</AboutTitleP>
                  <p>매월 {house?.maintenanceFee / 10000}만원</p>
                </AboutDetailDiv>
              </AboutEachDiv>
            </AboutDiv>
            <AboutDiv>
              <AboutP>상세정보</AboutP>
              <AboutEachDiv>
                <AboutDetailDiv>
                  <AboutTitleP>방 종류</AboutTitleP>
                  <p>{house?.roomType}</p>
                </AboutDetailDiv>
                <AboutDetailDiv>
                  <AboutTitleP>해당층/건물층</AboutTitleP>
                  <p>{house?.floorInfo}층</p>
                </AboutDetailDiv>
                <AboutDetailDiv>
                  <AboutTitleP>전용/공급면적</AboutTitleP>
                  <p>
                    {house?.exclusiveArea}m²/{house?.supplyArea}m² (
                    {Math.floor(house?.supplyArea / 3.3)}평)
                  </p>
                </AboutDetailDiv>
                <AboutDetailDiv>
                  <AboutTitleP>방 수/욕실 수</AboutTitleP>
                  <p>
                    {house?.roomCnt}개/{house?.bathroomCnt}개
                  </p>
                </AboutDetailDiv>
                <AboutDetailDiv>
                  <AboutTitleP>방향</AboutTitleP>
                  <p>{house?.direction}</p>
                </AboutDetailDiv>
                <AboutDetailDiv>
                  <AboutTitleP>건축물용도</AboutTitleP>
                  <p>{house?.lawUsage}</p>
                </AboutDetailDiv>
                <AboutDetailDiv>
                  <AboutTitleP>사용승인일</AboutTitleP>
                  <p>{house?.approvalDate}</p>
                </AboutDetailDiv>
                <AboutDetailDiv>
                  <AboutTitleP>최초등록일</AboutTitleP>
                  <p>{house?.articleConfirmYmd}</p>
                </AboutDetailDiv>
              </AboutEachDiv>
            </AboutDiv>
            <AboutDiv>
              <AboutP>중개사</AboutP>
              <AboutEachDiv>
                <AboutDetailDiv>
                  <AboutTitleP>지점명</AboutTitleP>
                  <p>{house?.realtorName}</p>
                </AboutDetailDiv>
                {/* <AboutDetailDiv>
                  <AboutTitleP>대표</AboutTitleP>
                  <p>이은정</p>
                </AboutDetailDiv> */}
                <AboutDetailDiv>
                  <AboutTitleP>주소</AboutTitleP>
                  <p>{house?.realtorAddress}</p>
                </AboutDetailDiv>
                <AboutDetailDiv>
                  <AboutTitleP>전화번호</AboutTitleP>
                  <p>{house?.realtorcellPhoneNo}</p>
                </AboutDetailDiv>
              </AboutEachDiv>
            </AboutDiv>
          </LeftDiv>
          <RightDiv>
            <InfoDiv>
              <InfoDetailDiv>
                <TitleP>현재 자산으로는</TitleP>
                <BlueP>
                  {MoneyFormatter(
                    Math.abs(house?.dealOrWarrantPrc_numeric - parseInt(holdingAsset) * 10000)
                  )}
                  원
                </BlueP>
                <TitleP>
                  {(house?.dealOrWarrantPrc_numeric - parseInt(holdingAsset) * 10000 < 0)
                    ? "여유가 있습니다."
                    : "더 필요합니다."}
                </TitleP>
              </InfoDetailDiv>
              <LineHr />
              <InfoDetailDiv>
                <TitleP>대출 시 예상되는 연 이자</TitleP>
                <BlueP>
                  {calculateInterestRate(house?.realEstateTypeName, user?.creditRating)}%
                </BlueP>
                <LightPDiv>
                  <LightP>가입 시 입력한 신용도를 기반으로</LightP>
                  <LightP>산정된 예상 이자율 입니다.</LightP>
                </LightPDiv>
              </InfoDetailDiv>
            </InfoDiv>
            <ChatDiv>
              <ChatBotDiv>
                <Image
                  src={butler}
                  height="50"
                  width="50"
                  onClick={handleChatClick}
                  alt="이미지 없음"
                />
              </ChatBotDiv>
              <AskDiv expanded={isChatOpen}>
                <div>
                  <AskP isVisible={isChatOpen}>{msg}</AskP>
                </div>
                <ChatMiddleDiv isVisible={isChatOpen}>
                  <Chatting messages={chatList} />
                </ChatMiddleDiv>
                <LoadingDiv>{loading && <LoadingP>당신의집사가 입력중입니다..</LoadingP>}</LoadingDiv>
                <ChatBottomDiv isVisible={isChatOpen}>
                  <MessageInput
                    type="text"
                    placeholder="메시지를 입력해주세요.."
                    value={chatMsg}
                    onChange={(e) => {
                      setChatMsg(e.target.value);
                    }}
                    onKeyDown={(e) => {
                      if (e.key === 'Enter') {
                        e.preventDefault();  // 이벤트의 기본 동작인 엔터 입력 방지
                        sendChat();  // sendChat 함수 호출
                      }
                    }}
                    disabled={loading}
                  />
                  <SvgBtn onClick={sendChat}>
                    <SendSvg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
                      <path d="M16.1 260.2c-22.6 12.9-20.5 47.3 3.6 57.3L160 376V479.3c0 18.1 14.6 32.7 32.7 32.7c9.7 0 18.9-4.3 25.1-11.8l62-74.3 123.9 51.6c18.9 7.9 40.8-4.5 43.9-24.7l64-416c1.9-12.1-3.4-24.3-13.5-31.2s-23.3-7.5-34-1.4l-448 256zm52.1 25.5L409.7 90.6 190.1 336l1.2 1L68.2 285.7zM403.3 425.4L236.7 355.9 450.8 116.6 403.3 425.4z" />
                    </SendSvg>
                  </SvgBtn>
                </ChatBottomDiv>
              </AskDiv>
            </ChatDiv>
          </RightDiv>
        </BottomDiv>
      </Container>
      <Footer />
    </main>
  );
};

export default DetailWithID;
