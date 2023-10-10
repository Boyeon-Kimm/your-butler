import React, { useState } from "react";
import styled from "styled-components";

interface RadioOption {
  label: string;
  value: string;
}

interface RadioProps {
  options: RadioOption[];
  selectedValue: string | null;
  onSelectionChange: (value: string) => void;
}

const Radio: React.FC<RadioProps> = ({ options, selectedValue, onSelectionChange }) => {
  const handleSelectionChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    onSelectionChange(event.target.value);
  };

  return (
    <div>
      {options.map((option, index) => (
        <StyledLabel key={index}>
          <input
            type="radio"
            value={option.value}
            checked={selectedValue === option.value}
            onChange={handleSelectionChange}
          />
          {option.label}
        </StyledLabel>
      ))}
    </div>
  );
};

const StyledLabel = styled.label`
  margin-right: 0.7rem;
  /* margin-bottom: 1rem; */
`;

export default Radio;
