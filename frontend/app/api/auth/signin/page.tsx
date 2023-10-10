import { getProviders, signIn } from "next-auth/react";
import { getServerSession } from "next-auth/next";
import authOptions from "@/Oauth/AuthOption";
import SocialSigninButton from "@/components/SocialSigninBtn/SocialSigninButton";
import AppBar from "@/components/AppBar";
import Footer from "@/components/Footer";

export default async function SignInPage() {
  const session = await getServerSession(authOptions);

  const providers = await getProviders();

  return (
    <>
      <AppBar backgroundColor="transparent" logo="greenlogo" color="#334835" user={null} />
      <SocialSigninButton providers={providers}></SocialSigninButton>
      <Footer />
    </>
    // </SocialSigninPage>
  );
}
