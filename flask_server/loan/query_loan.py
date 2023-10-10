import pymysql
import os

from langchain.agents import initialize_agent, AgentType
from langchain.chat_models import ChatOpenAI
from langchain.tools import Tool, StructuredTool
from langchain.utilities import SQLDatabase
from langchain_experimental.sql import SQLDatabaseChain
from langchain.prompts.prompt import PromptTemplate


def wonrigum_equal(loan_interest: float, lent_money: int = 100000000, repayment_months: int = 180) -> int:
    """ loan_intereest: 대출 상품의 금리, lent_money: 대출 상품을 통해 빌린 금액, repayment_months: 대출 상품의 상환 개월수를 입력받아 매달 갚아야 할 금액을 계산해주는 도구입니다."""
    loan_interest = float(loan_interest)
    loan_interest /= 100 if loan_interest >= 1 else loan_interest
    loan_interest /= 12
    return int(lent_money * (loan_interest * (1 + loan_interest) ** repayment_months) / ((1 + loan_interest) ** repayment_months - 1))


# def wongum_eqaul(original_money, loan_year_rate, repayment_months):
#     return original_money * loan_year_rate * (12 *)


def ilsibul(loan_interest: float, lent_money: int = 100000000, repayment_months: int = 180) -> int:
    """ loan_intereest: 대출 상품의 금리, lent_money: 대출 상품을 통해 빌린 금액, repayment_months: 대출 상품의 상환 개월수를 입력받아 만기 일시 상환 방식으로 상환할 때
    매달 갚아야 할 금액을 계산해주는 도구입니다."""
    loan_interest = float(loan_interest)
    loan_interest /= 100 if loan_interest >= 1 else loan_interest
    loan_month_rate = loan_interest / 12
    return int(lent_money + (lent_money * loan_month_rate * repayment_months))

def query_loan(chat: str) -> str:
    db = pymysql.connect(
        host="j9a405.p.ssafy.io",
        port=3306,
        user="root",
        passwd=f"{os.environ.get('MYSQL_PASSWORD')}",
        db="loan",
        charset="utf8",
        autocommit=True,
    )

    db = SQLDatabase.from_uri(f"mysql+pymysql://root:{os.environ.get('MYSQL_PASSWORD')}@j9a405.p.ssafy.io:3306/loan",
                              include_tables=["mortgage_loan", "jeonse_loan", "credit_loan"],
                              sample_rows_in_table_info=5)

    # print(db.table_info)
    llm = ChatOpenAI(model_name="gpt-3.5-turbo-16k", temperature=0)

    _DEFAULT_TEMPLATE = """Given an input question, first create a syntactically correct {dialect} query to run, then look at the results of the query and return the answer.
    Use the following format:

    Question: "Question here"
    SQLQuery: "SQL Query to run"
    SQLResult: "Result of the SQLQuery"
    Answer: Final answer here.
    
    SQLQuery에서 SELECT문 조회시에 *를 사용해 조회해야 하며, 최종 Answer는 한글로 작성되어야 한다.

    Only use the following tables:

    {table_info}

    If someone asks for the table credit_loan(개인신용대출), 최저금리를 조회하기 위해 사용되는 column 이름을 신용점수에 따라 적절히 선택하여야 한다.

    Question: {input}
    """

    # memory = ConversationBufferMemory(memory_key="chat_history", memory_type="list", max_len=10)

    PROMPT = PromptTemplate(
        input_variables=["input", "table_info", "dialect"], template=_DEFAULT_TEMPLATE
    )

    db_chain = SQLDatabaseChain.from_llm(
        llm, db, prompt=PROMPT, verbose=True, use_query_checker=True
    )

    # class WonrigumSchema(BaseModel):
    #     loan_interest: float = Field(description="대출 상품의 금리가 들어감.")
    #     lent_money: int = Field(description="대출 상품을 통해 빌린 금액이 들어감.")
    #     repayment_months: int = Field(description="대출 상품의 상환 개월수가 들어감")



    tools = [
        Tool(
            name="query At Database",
            # func=db_chain.run,
            func=query_loan_chain,
            description="유저가 원하는 대출 상품을 찾기 위해 데이터베이스에 접근할 때 유용한 도구입니다. (유저의 질문 : String)을 매개변수로 받습니다. 매개변수는 한글로 작성되어야 합니다."
        ),
        # StructuredTool.from_function(wonrigum_equal),
        # StructuredTool.from_function(ilsibul)
        Tool(
            name="원리금분할상환 대출 계산기",
            func=wonrigum_equal,
            description="유저가 원하는 대출 상품을 통해 대출을 받았을 때, 매달 갚아야 하는 금액을 계산해주는 도구입니다. (대출금리 : Float, 빌린 금액 : int, 상환 개월수 : int)를 매개변수로 받습니다."
            # args_schema=WonrigumSchema
        ),
        Tool(
            name="원금 만기 일시 상환 방식 계산기",
            func=ilsibul,
            description="유저가 원하는 대출 상품을 통해 원금 만기 일시 상환 방식으로 대출을 받았을 때, 매달 갚아야 하는 금액을 계산해주는 도구입니다. (대출금리 : Float, 빌린 금액 : int, 상환 개월수 : int)를 매개변수로 받습니다."
            # args_schema=WonrigumSchema
        ),
        # Tool(
        #     name="wongum_eqaul",
        #     func=wongum_eqaul,
        #     description="원금 균등 상환 방식으로 대출을 받았을 때, 매달 갚아야 하는 금액을 계산해주는 도구입니다."
        # )
    ]

    # response = db_chain.run(chat)
    agent = initialize_agent(
        tools=tools,
        llm=llm,
        # agent=AgentType.STRUCTURED_CHAT_ZERO_SHOT_REACT_DESCRIPTION,
        agent=AgentType.ZERO_SHOT_REACT_DESCRIPTION,
        verbose=True,
    )

    response = agent.run(chat)
    print(response)
    return response


global_chat = None

def chat_bot(chat: str) -> str:
    global global_chat
    global_chat = chat
    llm = ChatOpenAI(model_name="gpt-3.5-turbo-16k", temperature=0)
    print(f"chat : {chat}")

    _DEFAULT_TEMPLATE = """ 당신은 유저의 질문을 받고 다음과 같은 3가지 질문에 대한 답변을 생성하는 assistant 입니다.
    
    모든 질문과 대답은 한글로 생성되어야 합니다. 그리고 대답은 다음과 같은 세가지 형식으로 반환되어야만 합니다.
    
    ----
    
     1. 유저가 대출 상품 찾기를 요청했을때
     대답: "안녕하세요! 당신의 집사입니다. 회원님의 요청에 따라 저희가 추천드리는 상품은 XX은행의 XX상품입니다. 이 상품의 특징은 XXX이며, XXX입니다. 이 상품의 대출한도는 XXX이며, 대출금리는 XXX입니다. 이 상품의 월 상환금액은 XXX입니다. 이 상품의 대출한도와 대출금리는 은행의 대출 심사 결과에 따라 변동될 수 있으므로, 위의 결과는 참고용으로만 사용해주시기 바랍니다."
     
     ----
     
     2. 유저가 금융 용어 설명을 요청했을때
     대답:
     "용어": "용어에 대한 긴 설명"
     "용어2": "용어2에 대한 긴 설명"
     "용어3": "용어 3에 대한 긴 설명"
     위와 같은 형식으로 대답해야 합니다. 설명은 충분히 이해할 수 있도록 길어야 하며, 한 용어의 설명이 끝나면 줄바꿈 문자로 구분하여야 합니다.
     
     ----
     
     3. 그 외의 요청이 들어왔을 때는 자유로운 양식으로 대답을 생성하면 됩니다.
     
     모든 대답은 한글로 생성되어야만 합니다. 대답을 명확하게 생성할 수 없는 경우 "제가 알고 있는 정보가 없습니다." 라는 답변을 생성하십시오.
     """

    tools = [
        Tool(
            name="데이터베이스 쿼리",
            func=query_loan_chain,
            description="유저가 원하는 대출 상품을 찾기 위해 데이터베이스에 접근할 때 유용한 도구입니다. 이 도구의 input은 (chat : String) 이며, agent가 인식한 모든 chat을 매개변수로 넣습니다."
        ),
        Tool(
            name="금융 정보 조회",
            func=get_response_from_query,
            description="유저가 금융 용어에 대한 설명을 요구했을때 사용하기 유용한 도구입니다. 계산에는 이 도구를 사용하지 않습니다."
        ),
        Tool(
            name="원리금분할상환 대출 계산기",
            func=wonrigum_equal,
            description="유저가 원하는 대출 상품을 통해 대출을 받았을 때, 매달 갚아야 하는 금액을 계산해주는 도구입니다. (대출금리 : Float, 빌린 금액 : int, 상환 개월수 : int)를 매개변수로 받습니다."
        ),
    ]

    agent = initialize_agent(
        tools=tools,
        llm=llm,
        agent=AgentType.ZERO_SHOT_REACT_DESCRIPTION,
        verbose=True,
    )

    response = agent.run(input=chat, prompt=_DEFAULT_TEMPLATE)
    return response


if __name__ == "__main__":
    query_loan("국민은행에서 가장 낮은 금리로 받을 수 있는 전세자금대출을 2개 알려줘. 그 대출들이 어떤 금리를 가지고 있는지, 그리고 1억을 대출받아서 3년동안 원리금분할상환 방식으로 상환하면 한달마다 얼마를 갚아야 하는지도 알려줘. 대답은 한글이어야 해")

