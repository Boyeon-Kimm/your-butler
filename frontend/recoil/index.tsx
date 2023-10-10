'use client'

import { ReactNode } from 'react'
import { RecoilRoot } from 'recoil'
import {PropsWithChildren} from 'react'

const Recoil = ({ children }: PropsWithChildren) => {
    return <RecoilRoot>{children}</RecoilRoot>
}


export default Recoil;
