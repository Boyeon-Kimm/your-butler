import styled from "styled-components";
import { ButtonHTMLAttributes, CSSProperties } from "react";
import colors from "../../constants/colors";

interface ButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
  Variant?:
    | "yellowFilled"
    | "yellowOutline"
    | "yellowTonal"
    | "grayTonal"
    | "redFilled"
    | "grayOutline"
    | "redOutline";
  Kind?: "extraSmall" | "small" | "medium" | "big";
  Rounded?: "square" | "circle";
  onClick?: () => void;
  customStyle?: CSSProperties;
}

export default function Button({
  Kind = "extraSmall",
  Variant = "yellowFilled",
  Rounded,
  customStyle,
  onClick,
  ...props
}: ButtonProps) {
  return (
    <StyledButton
      Kind={Kind}
      Variant={Variant}
      Rounded={Rounded}
      onClick={onClick}
      style={customStyle}
      {...props}
    ></StyledButton>
  );
}

const StyledButton = styled.button<ButtonProps>((props) => ({
  // 스타일의 외부
  outline: "none",
  border: "0 solid transparent",
  cursor: "pointer",

  // 버튼 사이즈
  ...SIZE_VARIANT[props.Kind || "medium"],

  // 스타일 타입
  ...TYPE_VARIANTS[props.Variant || "yellowFilled"],

  // 라운드
  ...ROUNDED[props.Rounded || "extraSmall"],
}));

const SIZE_VARIANT = {
  extraSmall: {
    fontSize: "0.9rem",
    padding: "7px 16px",
  },
  small: {
    fontSize: "15px",
    padding: "11px 20px",
  },
  medium: {
    fontSize: "20px",
    padding: "14px 64px",
  },
  big: {
    fontSize: "25px",
    padding: "24px 96px",
    width: "100%",
    display: "border-box",
  },
};

const TYPE_VARIANTS = {
  redFilled: {
    backgroundColor: colors.red,
    color: colors.white,
    border: "solid 1px #FF5B5B",
  },
  redOutline: {
    backgroundColor: "transparent",
    color: colors.red,
    border: "solid 1px #FF5B5B",
  },
  yellowFilled: {
    backgroundColor: colors.yellow,
    color: colors.white,
    border: "solid 1px #FFC436",
  },
  yellowOutline: {
    backgroundColor: "transparent",
    color: colors.yellow,
    border: "solid 1px #FFC436",
  },
  yellowTonal: {
    backgroundColor: colors.yellow,
    color: colors.black,
    border: "solid 1px #FFC436",
  },
  grayOutline: {
    backgroundColor: "transparent",
    color: colors.darkgray,
    border: "solid 1px darkgray",
  },
};

const ROUNDED = {
  square: {
    borderRadius: "0.2rem",
  },
  circle: {
    borderRadius: "5rem",
  },
};
