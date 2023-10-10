import React, { useState, ChangeEvent } from "react";
import styled from "styled-components";

interface CheckboxProps {
  label?: string;
  value?: string;
  isChecked?: boolean;
  onChange?: (checked: boolean) => void;
}

const Checkbox: React.FC<CheckboxProps> = ({
  label,
  value,
  isChecked: propIsChecked,
  onChange,
}) => {
  const [isChecked, setIsChecked] = useState(propIsChecked || false);

  const handleCheckboxChange = () => {
    const newCheckedState = !isChecked;
    setIsChecked(newCheckedState);
    if (onChange) {
      onChange(newCheckedState);
    }
  };

  return (
    <InputDiv>
      <label>
        <input type="checkbox" value={value} checked={isChecked} onChange={handleCheckboxChange} />
        {label}
      </label>
    </InputDiv>
  );
};

const InputDiv = styled.div`
  display: flex;
  flex-direction: row;
  margin: 0.2rem 0;
  min-width: 6rem;
  max-width: 13rem;
`;

export default Checkbox;
