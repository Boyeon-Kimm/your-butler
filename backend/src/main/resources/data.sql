-- password = '12345678'
INSERT INTO MEMBER (id, email, password, role)
VALUES (100000, 'test@naver.com', '$2a$10$VdnR3vLc1dbHwACkq6/kzOUXC.J/N/fqBxSKAI7oV3Xvb03Mr7Cta', 'UNAUTH'),
       (100001, 'test1@naver.com', '$2a$10$VdnR3vLc1dbHwACkq6/kzOUXC.J/N/fqBxSKAI7oV3Xvb03Mr7Cta', 'MEMBER'),
       (100002, 'test2@naver.com', '$2a$10$VdnR3vLc1dbHwACkq6/kzOUXC.J/N/fqBxSKAI7oV3Xvb03Mr7Cta', 'ADMIN');