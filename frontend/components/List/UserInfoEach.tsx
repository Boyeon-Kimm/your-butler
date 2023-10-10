"use client";

import styled from "styled-components";
import colors from "@/constants/colors";

const UserInfoEach = ({ title, value }) => {
  return (
    <InfoEach>
      <BlueP>{title}</BlueP>
      <BoldP>{value}</BoldP>
    </InfoEach>
  );
};

const InfoEach = styled.div`
  gap: 0.7rem;
  display: flex;
  flex-direction: column;
  padding: 2rem;
  border-bottom: solid 1px ${colors.lightgray};
`;

const BlueP = styled.p`
  color: ${colors.blue};
  font-size: 0.9rem;
  font-weight: 600;
`;

const BoldP = styled.p`
  font-weight: 600;
  font-size: 1.1rem;
  color: ${colors.darkgray};
`;

export default UserInfoEach;
