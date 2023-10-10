// import NextAuth from 'next-auth'

// declare module "next-auth"{
//     interface Session{
//         user:{
//             idx:number,
//             id:string,
//             name:string,
//             age:string
//         }
//     }
// }

// next-auth.d.ts

interface User{
    name:String,
    email:String,
    age?:String,
    gender?:String,
    account?:Number,
    salary?:Number
};

export default User;