"use client";

import {redirect} from "next/navigation";
import React, {useEffect} from "react";
import styled from "styled-components";

const Invisible = styled.div`
    display:none;
`;
const ActivatedCheck = ({isActivated})=>{
    useEffect(()=>{
        if(isActivated === false){
            redirect("/create");
        }
    },[isActivated]);
    return(
        <Invisible>{isActivated}</Invisible>
    )
}

export default ActivatedCheck;