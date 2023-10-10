import json
import requests
import os
from pymongo import MongoClient

# from bs4 import BeautifulSoup
# from lxml import html
from urllib.request import Request, urlopen
from urllib.parse import urlencode, quote_plus, unquote
import pandas as pd

# import openpyxl
from pandas import DataFrame
from datetime import datetime

# MONGODB_URI = f"mongodb+srv://SSAFY:{os.environ.get('MONGO_DB_PASSWORD')}@cluster0.cehixkx.mongodb.net/local"
uri = f"mongodb+srv://ssafy:{os.environ.get('MONGO_DB_PASSWORD')}@cluster0.cehixkx.mongodb.net/?retryWrites=true&w=majority"
client = MongoClient(uri)
db = client.loan
collection = db.creditloan

column_mapping = {
    "dcls_month": "공시 제출월 [YYYYMM]",
    "fin_co_no": "금융회사 코드",
    "kor_co_nm": "금융회사 명",
    "fin_prdt_cd": "금융상품 코드",
    "fin_prdt_nm": "금융 상품명",
    "join_way": "가입 방법",
    "crdt_prdt_type": "대출종류 코드",
    "crdt_prdt_type_nm": "대출종류명",
    "cb_name": "CB 회사명",
    "dcls_strt_day": "공시 시작일",
    "dcls_end_day": "공시 종료일",
    "fin_co_subm_day": "금융회사 제출일 [YYYYMMDDHH24MI]",
    "crdt_lend_rate_type": "금리구분 코드",
    "crdt_lend_rate_type_nm": "금리구분",
    "crdt_grad_1": "900점 초과 [소수점 2자리]",
    "crdt_grad_4": "801~900점 [소수점 2자리]",
    "crdt_grad_5": "701~800점 [소수점 2자리]",
    "crdt_grad_6": "601~700점 [소수점 2자리]",
    "crdt_grad_10": "501~600점 [소수점 2자리]",
    "crdt_grad_11": "401~500점 [소수점 2자리]",
    "crdt_grad_12": "301~400점 [소수점 2자리]",
    "crdt_grad_13": "300점 이하 [소수점 2자리]",
    "crdt_grad_avg": "평균 금리 [소수점 2자리]",
}


# API를 호출해 데이터를 리스트로 변환하는 함수
def get_product(KEY, FINGROUP, PAGE):
    # 파이썬에서 인터넷을 연결하기 위해 urllib 패키지 사용. urlopen함수는 지정한 url과 소켓 통신을 할 수 있도록 자동 연결해줌
    url = f"http://finlife.fss.or.kr/finlifeapi/creditLoanProductsSearch.json?auth={KEY}&topFinGrpNo={FINGROUP}&pageNo={PAGE}"
    response = requests.get(url)
    data = response.json()
    base_list = data["result"]["baseList"]
    option_list = data["result"]["optionList"]

    for item in base_list:
        for eng_key in list(item.keys()):
            if eng_key in column_mapping:
                item[column_mapping[eng_key]] = item.pop(eng_key)

    for item in option_list:
        for eng_key in list(item.keys()):
            if eng_key in column_mapping:
                item[column_mapping[eng_key]] = item.pop(eng_key)

    print(f"base_list: {base_list}")
    print(f"option_list: {option_list}")
    return base_list, option_list


# 금융기관별 적금 정보 수집
# API 호출에 필요한 파라미터(필수)
# 금융기관별 코드 list: 데이터 명세 참고
fin_grp_list = [
    "020000",  # 은행
    "030200",  # 여신전문
    "030300",  # 저축은행
    "050000",  # 보험회사
    "060000",  # 금융투자
]

# API 호출에 필요한 파라미터(필수)
KEY = os.environ.get("LOAN_API_KEY")
PAGE = 1  # 조회하고자 하는 페이지 번호(1page로 충분한 듯)

# 스크래핑한 데이터를 담을 빈 list 정의
combined_saving_list = list()

# 금융기관별로 상품 정보를 호출한 후 의도한 스펙을 스크래핑하는 for-loop 구문
for grp in fin_grp_list:
    base_list, option_list = get_product(KEY, grp, PAGE)

    for base_product, option_product in zip(base_list, option_list):
        combined_product = {**base_product, **option_product}
        combined_saving_list.append(combined_product)

# 이게 있으면 직렬화에 적합하지 않은 값이 생기기 떄문에 오류가 발생함
collection.insert_many(combined_saving_list)

# json_str = json.dumps(combined_saving_list, ensure_ascii=False, indent=4)

# JSON 문자열을 파일로 저장
# with open('combined_saving_list.json', 'w', encoding='utf-8') as json_file:
#     json_file.write(json_str)

# # 리스트를 데이터프레임 형태로 변환
# print(f"combined_saving_list: {combined_saving_list}")
#
# combined_saving_df = DataFrame(combined_saving_list)
# combined_saving_df.rename(columns=column_mapping, inplace=True)
# print(f"combined_saving_df.head(): {combined_saving_df.head()}")
#
#
#
# # 데이터프레임을 엑셀로 저장
# date = datetime.today().strftime('%Y-%m-%d')
# combined_saving_df.to_excel(date + '_creditloan_information_3.xlsx', index=False)
