import styled from "styled-components";

const Chatting = ({ messages }) => {
  return (
    <ChatMiddleDiv>
      {messages && messages.map((message, index) => (
        <>
        <MessageContainer key={index} isBot={message.isBot}>
          <Message isBot={message.isBot}>{message.message}</Message>
        </MessageContainer>
        {message?.bank && 
        <MessageContainer key={`${index}bank`} isBot={message.isBot}>
          <Bank>
            <p>은행 정보</p>
            <p>{message?.bank.bankName}</p>
            <p>{message?.bank.bankTel}</p>
          </Bank>
          </MessageContainer>}
        {message?.loan && 
        <MessageContainer key={`${index}loan`} isBot={message.isBot}>
          <Loan>
            <p>대출 상품 정보</p>
            <p>{message?.loan.loanName}</p>
            <p>금리 : {message?.loan.loanInterest}%</p>
          </Loan>
          </MessageContainer>}
        </>
      ))}
    </ChatMiddleDiv>
  );
};

const ChatMiddleDiv = styled.div`
  height: 80%;
  padding: 0.5rem 0;
  display: block;
  flex-direction: column; /* column 방향으로 변경 */
`;

interface MessageConatinerProps {
  isBot: Boolean;
}

const MessageContainer = styled.div<MessageConatinerProps>`
  display: flex;
  flex-direction: ${(props) =>
    props.isBot
      ? "row-reverse"
      : "row"}; /* 메시지 오른쪽 정렬을 위해 reverse */
  margin-top: 0.5rem;
  padding-right: 0.5rem;
`;

const Message = styled.div<MessageConatinerProps>`
  padding: 1rem 1.5rem;
  width: fit-content;
  max-width: 13rem;
  border-radius: ${(props) =>
    props.isBot ? "2rem 2rem 0 2rem" : "2rem 2rem 2rem 0"};
  background-color: ${(props) => (props.isBot ? "#a32fff" : "lightgray")};
  color: ${(props) => (props.isBot ? "white" : "black")};
  line-height: 1.2rem;
`;

const Bank = styled.div`
  padding: 1rem 1.5rem;
  width: fit-content;
  max-width: 13rem;
  border-radius: 2rem 2rem 2rem 2rem;
  background-color: yellow;
  color: "black";
  line-height: 1.2rem;
`;

const Loan = styled.div`
  padding: 1rem 1.5rem;
  width: fit-content;
  max-width: 13rem;
  border-radius: 2rem 2rem 2rem 2rem;
  background-color: gray;
  color: white;
  line-height: 1.2rem;
`;

export default Chatting;
