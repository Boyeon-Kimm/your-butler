import { NextAuthOptions } from "next-auth"
import NaverProvider from "next-auth/providers/naver";
import KakaoProvider from "next-auth/providers/kakao";
import GoogleProvider from "next-auth/providers/google";
import authApi from "@/api/authApi";

const authOptions : NextAuthOptions = {
    secret: process.env.SECRET,
    providers: [
        NaverProvider({
          clientId: process.env.NAVER_ID || "",
          clientSecret: process.env.NAVER_SECRET || "",
        }),
        KakaoProvider({
          clientId: process.env.KAKAO_ID || "",
          clientSecret: process.env.KAKAO_SECRET || "",
        }),
        GoogleProvider({
          clientId: process.env.GOOGLE_ID || "",
          clientSecret : process.env.GOOGLE_SECRET || "",
        })
      ],
      callbacks:{
        async signIn({ user, account, profile, email }) {
          const res = await authApi.getUser(account.access_token, account.provider.toString());

          // @ts-ignore
          user.userData = {
            token : account.access_token,
            ...res.data.memberResponse,
            status : res.status,
            accessToken : res.data.token.accessToken
          };
          return true;
        },

        async redirect({url, baseUrl}) {
          return baseUrl;
        },

        async jwt({ token, user, account, profile }) {

            // @ts-ignore
            if(user?.userData){
              // @ts-ignore
              token.userData = user.userData;
            }
            return token;
          },

        async session({ session, user, token }) {
          // @ts-ignore
          session = token;
          return session
        },
      },
      pages:{
        signIn:"/auth/signin"
      },
};

export default authOptions;