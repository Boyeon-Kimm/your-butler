"use client";

import { useSearchParams } from "next/navigation";
import { ClientSafeProvider, signIn } from "next-auth/react";
import styled from "styled-components";
import colors from "@/constants/colors";
import Image from "next/image";
import greenhouse from "@/public/assets/greenhouse.png";
import googleLogo from "@/public/assets/google.png";

type IProps = {
  providers: Record<string, ClientSafeProvider>;
};

export default function SocialSigninButton({ providers }: IProps) {
  const searchParams = useSearchParams();
  const callbackUrl = searchParams.get("callbackUrl");

  return (
    <Container>
      <Wrapper>
        <TitleDiv>
          <StyledImg src={greenhouse} layout="contain" alt="Logo" />
          <TitleP>당신의집사</TitleP>
          <PDiv>
            <p>간편하게 로그인하고</p>
            <p>다양한 서비스를 이용해보세요</p>
          </PDiv>
        </TitleDiv>
        <ButtonsWrapper>
          {Object.values(providers).map((provider) => (
            <ButtonWrapper key={provider.name}>
              {provider.name === "Naver" && (
                <NaverSigninBtn onClick={() => signIn(provider.id, { callbackUrl })}>
                  <StyledSvg role="img" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                    <title>Naver</title>
                    <path d="M16.273 12.845 7.376 0H0v24h7.726V11.156L16.624 24H24V0h-7.727v12.845Z" />
                  </StyledSvg>
                  {/* 로그인 */}
                </NaverSigninBtn>
              )}
              {provider.name === "Kakao" && (
                <KakaoSigninBtn onClick={() => signIn(provider.id, { callbackUrl })}>
                  <StyledSvg role="img" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                    <title>Kakao</title>
                    <path d="M3.0743 10.4403l.655.4728-1.6101 2.0192 1.8647 2.2373-.646.5004-2.201-2.6924zm-2.2376 5.102H0V8.5121l.8367-.182zm20.944-4.3837c-.4364 0-.7715.1637-1.0049.4912-.2338.3274-.3505.8064-.3505 1.437 0 .6247.1167 1.096.3505 1.4143.2334.3183.5685.4775 1.0049.4775.4423 0 .7804-.1593 1.0143-.4775.2332-.3182.35-.7896.35-1.4142 0-.6307-.1168-1.1097-.35-1.4371-.234-.3275-.572-.4912-1.0143-.4912m0-.673c.691 0 1.234.2245 1.6277.673.3944.4488.5916 1.0915.5916 1.9283 0 .8244-.1955 1.4583-.5868 1.901-.3909.4422-.9356.6637-1.6325.6637-.691 0-1.234-.2215-1.6277-.6638-.3944-.4426-.5916-1.0765-.5916-1.901 0-.8367.1984-1.4794.5957-1.9282.3973-.4485.9385-.673 1.6236-.673m-5.534 4.4658a1.496 1.496 0 0 0 .3576-.0456 2.8804 2.8804 0 0 0 .3713-.1181 2.0066 2.0066 0 0 0 .3488-.1774 2.0778 2.0778 0 0 0 .2895-.2229v-1.1641h-.8693c-.441 0-.7626.0758-.9645.2274-.2025.1516-.3031.391-.3031.7185 0 .5214.2563.7822.7697.7822m-1.5704-.7458c0-.5032.1682-.887.5045-1.1504.337-.2638.826-.396 1.4691-.396h.964v-.3182c0-.77-.3393-1.155-1.0185-1.155-.2184 0-.447.0304-.6869.091-.2398.0608-.4594.1365-.659.2274l-.2457-.5913c.2487-.1394.517-.2469.8047-.323.2878-.0754.5685-.1136.8414-.1136 1.176 0 1.7646.6276 1.7646 1.8826v3.1833h-.6188l-.1-.5457c-.2488.2001-.5134.3547-.796.464-.2817.1092-.55.1637-.8046.1637-.4429 0-.7899-.1258-1.0416-.3775-.2515-.2517-.3772-.5987-.3772-1.0413m-1.6508-3.7653l.655.4728-1.6095 2.0192 1.864 2.2373-.6454.5004-2.201-2.6924zm-2.237 5.102h-.8367V8.5121l.8368-.182zm-4.4936-.5909c.1148 0 .2339-.0151.3576-.0456a2.8794 2.8794 0 0 0 .3713-.1181 1.9842 1.9842 0 0 0 .3488-.1774 2.0477 2.0477 0 0 0 .29-.2229v-1.1641h-.8698c-.4404 0-.762.0758-.9645.2274-.202.1516-.3031.391-.3031.7185 0 .5214.2563.7822.7697.7822m-1.5704-.7458c0-.5032.1682-.887.5052-1.1504.3363-.2638.826-.396 1.4684-.396h.9646v-.3182c0-.77-.3399-1.155-1.019-1.155-.218 0-.4471.0304-.6863.091-.2398.0608-.4595.1365-.6597.2274l-.2457-.5913c.2487-.1394.517-.2469.8053-.323.2878-.0754.5684-.1136.8408-.1136 1.1766 0 1.7646.6276 1.7646 1.8826v3.1833h-.6182l-.1001-.5457c-.2487.2001-.514.3547-.7958.464-.282.1092-.5501.1637-.8053.1637-.4423 0-.7893-.1258-1.041-.3775-.2516-.2517-.3778-.5987-.3778-1.0413Z" />
                  </StyledSvg>
                  {/* {provider.name} 로그인 */}
                </KakaoSigninBtn>
              )}
              {provider.name === "Google" && (
                <GoogleSigninBtn onClick={() => signIn(provider.id, { callbackUrl })}>
                  <LogoImg src={googleLogo} layout="contain" alt="Google" />
                  {/* {provider.name} 로그인 */}
                </GoogleSigninBtn>
              )}
            </ButtonWrapper>
          ))}
        </ButtonsWrapper>
      </Wrapper>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;

  ::selection {
    background-color: #afffe3;
  }
`;

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  text-align: center;
  justify-content: center;
  border: solid 1px ${colors.lightgray};
  border-radius: 0.4rem;
  padding: 3rem;
  margin: 6.5rem;
  width: 25rem;
`;

const ButtonsWrapper = styled.div`
  display: flex;
  justify-content: center;
  gap: 1rem;
  padding: 2rem;
`;

const ButtonWrapper = styled.div``;

const NaverSigninBtn = styled.button`
  background-color: #03c75a;
  border: solid 2px #03c75a;
  color: #ffffff;
  font-weight: 500;
  border-radius: 100%;
  cursor: pointer;
  padding: 1rem;
  height: 4.5rem;
  width: 4.5rem;
`;

const KakaoSigninBtn = styled.button`
  background-color: #ffcd00;
  border: solid 2px #ffcd00;
  color: #000000;
  font-weight: 500;
  border-radius: 100%;
  cursor: pointer;
  padding: 1rem;
  height: 4.5rem;
  width: 4.5rem;
`;

const GoogleSigninBtn = styled.button`
  background-color: #ffffff;
  border: solid 1px #aba6a6;
  font-weight: 500;
  border-radius: 100%;
  cursor: pointer;
  padding: 1rem;
  height: 4.5rem;
  width: 4.5rem;
  -webkit-user-select: none;
`;

const TitleP = styled.p`
  font-weight: 600;
  font-size: 1.8rem;
  color: ${colors.darkgreen};
`;

const StyledImg = styled(Image)`
  height: 3.5rem;
  width: 3.5rem;
`;

const TitleDiv = styled.div`
  gap: 1rem;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-bottom: 2rem;
  border-bottom: solid 1px ${colors.lightgray};
`;

const StyledSvg = styled.svg`
  height: 2rem;
  width: 2rem;
`;

const LogoImg = styled(Image)`
  height: 2rem;
  width: 2rem;
`;

const PDiv = styled.div`
  display: flex;
  flex-direction: column;
  gap: 0.3rem;
`;
