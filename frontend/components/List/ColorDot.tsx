import styled from "styled-components";
import colors from "@/constants/colors";

const ColorBox = styled.div<{backgroundColor?:string}>`
  background-color: ${({backgroundColor}) => backgroundColor || "transparent"};
  box-shadow: 0 0 3px 3px ${({backgroundColor}) => backgroundColor || "transparent"};
  border-radius: 5rem;
  width: 10px;
  height: 10px;
`;

interface ColorDotProps {
  color?: string;
}

const ColorDot = ({ color }: ColorDotProps) => {
  return <ColorBox backgroundColor={color || "transparent"}></ColorBox>;
};

export default ColorDot;
