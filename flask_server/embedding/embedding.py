import os

from langchain import FAISS, LLMChain
from langchain.chat_models import ChatOpenAI
from langchain.document_loaders import TextLoader
from langchain.embeddings import OpenAIEmbeddings
from langchain.prompts import (
    SystemMessagePromptTemplate,
    HumanMessagePromptTemplate,
    ChatPromptTemplate,
)
from langchain.text_splitter import RecursiveCharacterTextSplitter
from langchain.document_loaders import PyPDFLoader
from PyPDF2 import PdfReader


def load_single_document(file_path):
    loader = TextLoader(file_path, encoding="utf-8")
    return loader.load()[0]


def load_documents(source_dir):
    all_files = os.listdir(source_dir)
    return [
        load_single_document(f"{source_dir}/{file_name}") for file_name in all_files
    ]


# def get_response_from_query(vector_db, query, target, k):
def get_response_from_query(vector_db, query):
    # FAISS 먼저 적용하고 오기
    # docs = vector_db.similarity_search(query, k=k)
    docs = vector_db.similarity_search(query)
    chat = ChatOpenAI(model_name="gpt-3.5-turbo-16k", temperature=0)

    template = """
    당신은 부동산을 구매하려는 사용자에게 금융, 부동산과 관련된 정보를 제공하는 assistant입니다.
    Document retrieved from your DB : {docs}
    
    Answer the questions referring to the documents which you Retrieved from DB as much as possible.
    If you fell like you don't have enough-information to answer the question, say "제가 알고 있는 정보가 없습니다."
    """
    # If you fell like you don't have enough-information to answer the question, say "제가 알고 있는 정보가 없습니다."
    system_message_prompt = SystemMessagePromptTemplate.from_template(template)

    human_template = "Answer the following question IN KOREAN: {question}"
    human_message_prompt = HumanMessagePromptTemplate.from_template(human_template)

    chat_prompt = ChatPromptTemplate.from_messages(
        [system_message_prompt, human_message_prompt]
    )

    chain = LLMChain(llm=chat, prompt=chat_prompt)
    response = chain.run(docs=docs, question=query)
    return response


def init_mongo_db():  # Mongo DB 생성
    return None


def json_to_vector():  # Vector DB 생성
    if not os.path.exists("DB/vector"):
        os.makedirs("DB/vector")

    embedding = OpenAIEmbeddings(openai_api_key=os.environ.get("OPENAI_API_KEY"))
    file_path = "DB/json"
    list_name = ["test"]

    for name in list_name:
        documents = load_documents(f"{file_path}/{name}")
        text_splitter = RecursiveCharacterTextSplitter(
            chunk_size=1000, chunk_overlap=200
        )
        docs = text_splitter.split_documents(documents)
        db = FAISS.from_documents(docs, embedding)
        db.save_local(f"DB/vector/{name}")
        print(f"DB/vector/{name} is saved")


def pdf_to_vector():
    loader = PyPDFLoader("DB/pdf")
    return


def pdf_to_txt():
    # 18쪽부터 369쪽 까지 읽어서 txt파일로 변경하기
    start_page, end_page = 18, 369
    reader = PdfReader("../DB/pdf/korea_bank_700_information.pdf")
    number_of_pages = len(reader.pages)
    text = ""

    for i in range(start_page, end_page + 1):
        page = reader.pages[i]
        extract_text = page.extract_text()
        text += extract_text.strip()

    text = text.replace("\n", " ")

    with open("../DB/txt/korea_bank_700_information.txt", "w", encoding="utf-8") as f:
        f.write(text)

    print("pdf -> txt 변환이 완료되었습니다.")


def txt_to_vector():
    if not os.path.exists("../DB/json"):
        os.makedirs("../DB/json")

    embedding = OpenAIEmbeddings(openai_api_key=os.environ.get("OPENAI_API_KEY"))
    file_path = "../DB/txt/"
    transcript = load_documents(file_path)
    text_splitter = RecursiveCharacterTextSplitter(chunk_size=1000, chunk_overlap=200)
    docs = text_splitter.split_documents(transcript)
    db = FAISS.from_documents(docs, embedding)
    db.save_local("../DB/vector/korea_bank_700_information")
    print("텍스트 -> vector 변환이 완료되었습니다.")


# pdf_to_txt()
embedding = OpenAIEmbeddings(openai_api_key=os.environ.get("OPENAI_API_KEY"))
vector_db = FAISS.load_local("../DB/vector/korea_bank_700_information", embedding)
query = "원리금상환대출이 뭐야?"
print(get_response_from_query(vector_db, query))
