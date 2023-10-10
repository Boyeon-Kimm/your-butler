import pymysql
import requests
import os


def insert_db(data):
    insert_query = """
        INSERT INTO 개인신용대출 (
            `금융회사명`,
            `금융상품명`,
            `가입방법`,
            `CB회사명`,
            `대출종류명`,
            `금리구분`,
            `900점초과`,
            `801~900점`,
            `701~800점`,
            `601~700점`,
            `501~600점`,
            `401~500점`,
            `301~400점`,
            `300점이하`,
            `평균금리`
        ) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
        """

    values = (
        data["kor_co_nm"],
        data["fin_prdt_nm"],
        data["join_way"],
        data["cb_name"],
        data["crdt_prdt_type_nm"],
        data["crdt_lend_rate_type_nm"],
        data["crdt_grad_1"],
        data["crdt_grad_4"],
        data["crdt_grad_5"],
        data["crdt_grad_6"],
        data["crdt_grad_10"],
        data["crdt_grad_11"],
        data["crdt_grad_12"],
        data["crdt_grad_13"],
        data["crdt_grad_avg"],
    )

    cursor.execute(insert_query, values)


db = pymysql.connect(
    host="127.0.0.1",
    port=3306,
    user="ssafy",
    passwd=f"{os.environ.get('MYSQL_PASSWORD')}",
    db="loan",
    charset="utf8",
    autocommit=True,
)

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
            item[eng_key] = item.pop(eng_key)

    for item in option_list:
        for eng_key in list(item.keys()):
            item[eng_key] = item.pop(eng_key)

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

cursor = db.cursor()

# 금융기관별로 상품 정보를 호출한 후 의도한 스펙을 스크래핑하는 for-loop 구문
for grp in fin_grp_list:
    base_list, option_list = get_product(KEY, grp, PAGE)

    for base_product, option_product in zip(base_list, option_list):
        combined_product = {**base_product, **option_product}
        insert_db(combined_product)
        combined_saving_list.append(combined_product)

print(combined_saving_list)
db.close()


# json_str = j
