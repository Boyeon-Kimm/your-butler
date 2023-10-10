/** @type {import('next').NextConfig} */
const nextConfig = {
    compiler:{
        styledComponents:true
    },
    env: {
        NEXTAUTH_URL: process.env.NEXTAUTH_URL,
        SECRET: process.env.SECRET
    },
    images: {
        domains: ['landthumb-phinf.pstatic.net'],
    },
    async rewrites() {
        return [
            {
                source: '/oauth2.0/:path*', // url이 source에 해당될 경우
                destination: 'https://nid.naver.com/oauth2.0/:path*', // destination으로 redirect
            },
        ];
    },
}

module.exports = nextConfig
