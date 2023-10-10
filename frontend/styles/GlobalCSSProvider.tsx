"use client";

import { PropsWithChildren } from "react";
import GlobalStyles from "@/styles/GlobalStyles";

export function GlobalCssProvider({ children }: PropsWithChildren) {
  return (
    <>
      <GlobalStyles />
      {children}
    </>
  );
}

