'use client'

import React from "react";
import {userState, userStateTypes} from "@/recoil/atom/userState";
import {useRecoilState} from "recoil";

const UserNameInput = ()=>{
    const [user, setUser] = useRecoilState<userStateTypes>(userState);
    const updateName = (e:React.ChangeEvent<HTMLInputElement>) => {
        setUser({name:e.target.value});
    };

    return(
        <>
        <p>Hello,{user.name}</p>
        <input
        type="text"
        name="name"
        id="input_name"
        onChange={updateName}
        placeholder="Enter your name"/>
        </>
    );
}

export default UserNameInput;
