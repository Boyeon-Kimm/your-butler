import { createGlobalStyle } from "styled-components";
import reset from "styled-reset";

const GlobalStyles = createGlobalStyle`
  ${reset};

  @font-face {
    font-family: 'IBMPlexSansKR';
    font-weight: 400;
    font-style: normal;
    src: url('@/public/fonts/IBMPlexSansKR-Light.ttf') format('ttf');
  }

  @font-face {
    font-family: 'IBMPlexSansKR';
    font-weight: 700;
    font-style: normal;
    src: url('@/public/fonts/IBMPlexSansKR-Bold.ttf') format('ttf');
  }

  body {
    font-family: 'IBMPlexSansKR';
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    min-width: 80rem;
  }
`;

export default GlobalStyles;
