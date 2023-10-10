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

uri = f"mongodb+srv://ssafy:{os.environ.get('MONGO_DB_PASSWORD')}@cluster0.cehixkx.mongodb.net/?retryWrites=true&w=majority"
client = MongoClient(uri)
db = client.loan
collection = db.homeloan

column_mapping = {
    "dcls_month": "공시 제출월",
    "fin_co_no": "금융회사 코드",
    "fin_prdt_cd": "금융상품 코드",
    "kor_co_nm": "금융회사 명",
    "fin_prdt_nm": "금융 상품명",
    "join_way": "가입 방법",
    "loan_inci_expn": "대출 부대비용",
    "erly_rpay_fee": "중도상환 수수료",
    "dly_rate": "연체 이자율",
    "loan_lmt": "대출한도",
    "dcls_strt_day": "공시 시작일",
    "dcls_end_day": "공시 종료일",
    "fin_co_subm_day": "금융회사 제출일",
    "mrtg_type": "담보유형 코드",
    "mrtg_type_nm": "담보유형",
    "rpay_type": "대출상환유형 코드",
    "rpay_type_nm": "대출상환유형",
    "lend_rate_type": "대출금리유형 코드",
    "lend_rate_type_nm": "대출금리유형",
    "lend_rate_min": "대출금리_최저",
    "lend_rate_max": "대출금리_최고",
    "lend_rate_avg": "전월 취급 평균금리",
}


# API를 호출해 데이터를 리스트로 변환하는 함수
def get_product(KEY, FINGROUP, PAGE):
    # 파이썬에서 인터넷을 연결하기 위해 urllib 패키지 사용. urlopen함수는 지정한 url과 소켓 통신을 할 수 있도록 자동 연결해줌
    url = f"http://finlife.fss.or.kr/finlifeapi/mortgageLoanProductsSearch.json?auth={KEY}&topFinGrpNo={FINGROUP}&pageNo={PAGE}"
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

collection.insert_many(combined_saving_list)
# json_str = json.dumps(combined_saving_list, ensure_ascii=False, indent=4)
#
# # JSON 문자열을 파일로 저장
# with open('joodamdae_combined_saving_list.json', 'w', encoding='utf-8') as json_file:
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
