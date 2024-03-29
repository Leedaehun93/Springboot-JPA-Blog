-- -- Table , 시퀀스 등 구조 정의
-- DROP SEQUENCE SQ_DEPT;
-- CREATE SEQUENCE SQ_DEPT START WITH 50 INCREMENT BY 10;
--
-- DROP SEQUENCE SQ_EMP;
-- CREATE SEQUENCE SQ_EMP START WITH 8000 INCREMENT BY 1;
--
-- DROP SEQUENCE SQ_QNA;
-- CREATE SEQUENCE SQ_QNA START WITH 1 INCREMENT BY 1;
--
-- DROP SEQUENCE SQ_CUSTOMER;
-- CREATE SEQUENCE SQ_CUSTOMER START WITH 1 INCREMENT BY 1;
--
-- DROP SEQUENCE SQ_FAQ;
-- CREATE SEQUENCE SQ_FAQ START WITH 1 INCREMENT BY 1;
--
-- DROP SEQUENCE SQ_CINEMA_FAQ;
-- CREATE SEQUENCE SQ_CINEMA_FAQ START WITH 1 INCREMENT BY 1;
--
-- DROP SEQUENCE SQ_REPLY_BOARD;
-- CREATE SEQUENCE SQ_REPLY_BOARD INCREMENT BY 1 START WITH 1;
-- DROP SEQUENCE SQ_THREAD_BOARD;
-- CREATE SEQUENCE SQ_THREAD_BOARD INCREMENT BY 1 START WITH 1;
--
-- DROP SEQUENCE SQ_SIMPLE_PRODUCT;
-- CREATE SEQUENCE SQ_SIMPLE_PRODUCT START WITH 1 INCREMENT BY 1;
--
-- DROP SEQUENCE SQ_SIMPLE_CART;
-- CREATE SEQUENCE SQ_SIMPLE_CART START WITH 1 INCREMENT BY 1;
--
-- DROP SEQUENCE SQ_PRODUCT;
-- CREATE SEQUENCE SQ_PRODUCT START WITH 1 INCREMENT BY 1;
--
-- DROP SEQUENCE SQ_CART;
-- CREATE SEQUENCE SQ_CART START WITH 1 INCREMENT BY 1;
--
-- DROP TABLE TB_EMP CASCADE CONSTRAINT;
-- DROP TABLE TB_DEPT CASCADE CONSTRAINT;
--
-- -- 공통코드 테이블은 시퀀스는 사용하지 않음
-- -- 공통코드 테이블의 등록된 코드는 향후에 않쓰이더라도 삭제/수정하지 않음 : 데이터가 많지않아 오버헤드가 없음
-- DROP TABLE TB_CODE_CATEGORY CASCADE CONSTRAINT;
-- DROP TABLE TB_CODE CASCADE CONSTRAINT;
--
-- DROP TABLE TB_QNA CASCADE CONSTRAINT;
-- DROP TABLE TB_CUSTOMER CASCADE CONSTRAINT;
--
-- DROP TABLE TB_FAQ CASCADE CONSTRAINT;
-- DROP TABLE TB_CINEMA_FAQ CASCADE CONSTRAINT;
--
-- DROP TABLE TB_REPLY_BOARD CASCADE CONSTRAINT;
-- DROP TABLE TB_THREAD_BOARD CASCADE CONSTRAINT;
--
-- DROP TABLE TB_SIMPLE_PRODUCT CASCADE CONSTRAINT;
-- DROP TABLE TB_SIMPLE_CART CASCADE CONSTRAINT;
-- DROP TABLE TB_PRODUCT CASCADE CONSTRAINT;
-- DROP TABLE TB_CART CASCADE CONSTRAINT;
--
-- DROP TABLE TB_FILE_DB CASCADE CONSTRAINT;
-- DROP TABLE TB_GALLERY CASCADE CONSTRAINT;
--
-- -- 부서 테이블
-- CREATE TABLE TB_DEPT
-- (
--     DNO         NUMBER NOT NULL PRIMARY KEY,
--     DNAME       VARCHAR2(255),
--     LOC         VARCHAR2(255),
--     DELETE_YN   VARCHAR2(1) DEFAULT 'N',
--     INSERT_TIME VARCHAR2(255),
--     UPDATE_TIME VARCHAR2(255),
--     DELETE_TIME VARCHAR2(255)
-- );
--
-- -- 사원 테이블 : 문제
-- CREATE TABLE TB_EMP
-- (
--     ENO         NUMBER NOT NULL PRIMARY KEY,
--     ENAME       VARCHAR2(255),
--     JOB         VARCHAR2(255),
--     MANAGER     NUMBER,
--     HIREDATE    VARCHAR2(255),
--     SALARY      NUMBER,
--     COMMISSION  NUMBER,
--     DNO         NUMBER,
--     DELETE_YN   VARCHAR2(1) DEFAULT 'N',
--     INSERT_TIME VARCHAR2(255),
--     UPDATE_TIME VARCHAR2(255),
--     DELETE_TIME VARCHAR2(255)
-- );
--
-- -- 코드성 테이블 : 공통 코드 유형(대분류) 테이블
-- CREATE TABLE TB_CODE_CATEGORY
-- (
--     CATEGORY_ID   NUMBER NOT NULL
--         CONSTRAINT PK_CODE_CATEGORY PRIMARY KEY,
--     CATEGORY_NAME VARCHAR2(255)
-- );
--
-- -- 코드성 테이블 : 공통 코드(소분류) 테이블
-- CREATE TABLE TB_CODE
-- (
--     CODE_ID     NUMBER NOT NULL
--         CONSTRAINT PK_CODE PRIMARY KEY,
--     CODE_NAME   VARCHAR2(255),
--     CATEGORY_ID NUMBER NOT NULL
--         CONSTRAINT FK_CODE_CATEGORY_CODE REFERENCES TB_CODE_CATEGORY (CATEGORY_ID),
--     USE_YN      VARCHAR(1) DEFAULT 'Y'
-- );
--
-- -- qna
-- CREATE TABLE TB_QNA
-- (
--     QNO         NUMBER NOT NULL
--         CONSTRAINT PK_QNA PRIMARY KEY,
--     QUESTION    VARCHAR2(255),
--     ANSWER      VARCHAR2(255),
--     QUESTIONER  VARCHAR2(255),
--     ANSWERER    VARCHAR2(255),
--     DELETE_YN   VARCHAR2(1) DEFAULT 'N',
--     INSERT_TIME VARCHAR2(255),
--     UPDATE_TIME VARCHAR2(255),
--     DELETE_TIME VARCHAR2(255)
-- );
--
-- -- 고객 테이블 : 문제
-- CREATE TABLE TB_CUSTOMER
-- (
--     CID         NUMBER NOT NULL
--         CONSTRAINT PK_CUSTOMER PRIMARY KEY,
--     FULL_NAME   VARCHAR2(255),
--     EMAIL       VARCHAR2(255),
--     PHONE       VARCHAR2(255),
--     DELETE_YN   VARCHAR2(1) DEFAULT 'N',
--     INSERT_TIME VARCHAR2(255),
--     UPDATE_TIME VARCHAR2(255),
--     DELETE_TIME VARCHAR2(255)
-- );
--
-- -- faq 테이블
-- CREATE TABLE TB_FAQ
-- (
--     NO          NUMBER NOT NULL
--         CONSTRAINT PK_FAQ PRIMARY KEY, -- faq 번호
--     TITLE       VARCHAR2(255),         -- 제목
--     CONTENT     VARCHAR2(255),         -- 내용
--     DELETE_YN   VARCHAR2(1) DEFAULT 'N',
--     INSERT_TIME VARCHAR2(255),
--     UPDATE_TIME VARCHAR2(255),
--     DELETE_TIME VARCHAR2(255)
-- );
--
-- -- 영화 faq 테이블 : 문제
-- CREATE TABLE TB_CINEMA_FAQ
-- (
--     CFNO        NUMBER NOT NULL
--         CONSTRAINT PK_CINEMA_FAQ PRIMARY KEY, -- faq 번호
--     QUESTION    VARCHAR2(255),                -- 제목
--     ANSWER      VARCHAR2(4000),               -- 내용
--     SORT_ORDER  NUMBER DEFAULT 0,             -- 출력순서 정렬순서 바꾸기 컬럼 (숫자가 클수록 먼저 출력됨)
--     DELETE_YN   VARCHAR2(1) DEFAULT 'N',
--     INSERT_TIME VARCHAR2(255),
--     UPDATE_TIME VARCHAR2(255),
--     DELETE_TIME VARCHAR2(255)
-- );
--
-- -- 답변형 게시판
-- CREATE TABLE TB_REPLY_BOARD
-- (
--     BID           NUMBER NOT NULL
--         CONSTRAINT PK_REPLY_BOARD PRIMARY KEY, -- 게시판번호
--     BOARD_TITLE   VARCHAR2(256),               -- 제목
--     BOARD_CONTENT VARCHAR2(255),               -- 내용
--     BOARD_WRITER  VARCHAR2(255),               -- 작성자
--     VIEW_CNT      NUMBER DEFAULT 0,            -- 조회수
--     BOARD_GROUP   NUMBER,                      -- 트리구조 최상위 부모 노드( 부모가 있을 경우 : 부모번호, 없을 경우 : 자신의 게시판번호 )
--     BOARD_PARENT  NUMBER,                      -- 자신의 부모 노드 ( 부모가 있을 경우 : 부모번호, 없을 경우 : 0 )
--     DELETE_YN     VARCHAR2(1) DEFAULT 'N',
--     INSERT_TIME   VARCHAR2(255),
--     UPDATE_TIME   VARCHAR2(255),
--     DELETE_TIME   VARCHAR2(255)
-- );
--
-- -- 답변형 게시판 : 문제
-- CREATE TABLE TB_THREAD_BOARD
-- (
--     tid         number not null
--         constraint pk_thread_board primary key, -- 게시판번호
--     subject     varchar2(256),                  -- 제목
--     main_text   varchar2(255),                  -- 내용
--     writer      varchar2(255),                  -- 작성자
--     views       number default 0,               -- 조회수
--     tgroup      number,                         -- 트리구조 최상위 부모 노드( 부모가 있을 경우 : 부모번호, 없을 경우 : 자신의 게시판번호 )
--     tparent     number,                         -- 자신의 부모 노드 ( 부모가 있을 경우 : 부모번호, 없을 경우 : 0 )
--
--     DELETE_YN   VARCHAR2(1) DEFAULT 'N',
--     INSERT_TIME VARCHAR2(255),
--     UPDATE_TIME VARCHAR2(255),
--     DELETE_TIME VARCHAR2(255)
-- );
--
-- -- 마스터성 테이블 : 점이력 관리 않함
-- -- 1) 추가/수정만 가능
-- -- 2) 삭제 않하고 사용여부만 관리
-- -- 사용않하는 레코드는 향후에 배치잡으로 일괄 삭제
-- -- 심픔 상품 테이블
-- CREATE TABLE TB_SIMPLE_PRODUCT
-- (
--     SPNO       NUMBER NOT NULL
--         CONSTRAINT PK_SIMPLE_PRODUCT PRIMARY KEY, -- 상품번호
--     CODE_ID    NUMBER,                            -- 상품종류코드
--     TITLE      VARCHAR2(255),                     -- 상품명
--     IMG_PATH   VARCHAR2(255),                     -- 이미지 경로
--     UNIT_PRICE NUMBER,                            -- 단가
--     USE_YN     VARCHAR2(1) DEFAULT 'Y'            -- 사용여부
-- );
--
-- -- 심플 장바구니 테이블
-- CREATE TABLE TB_SIMPLE_CART
-- (
--     SCNO        NUMBER NOT NULL
--         CONSTRAINT PK_SIMPLE_CART PRIMARY KEY,                                 -- 장바구니번호
--     SPNO        NUMBER
--         CONSTRAINT FK_SIMPLE_PRODUCT_CART REFERENCES TB_SIMPLE_PRODUCT (SPNO), -- 상품번호
--     CART_COUNT  NUMBER DEFAULT 0,                                              -- 장바구니 상품개수
--     DELETE_YN   VARCHAR2(1) DEFAULT 'N',
--     INSERT_TIME VARCHAR2(255),
--     UPDATE_TIME VARCHAR2(255),
--     DELETE_TIME VARCHAR2(255)
-- );
--
-- -- 마스터성 테이블 : 점이력 관리 않함
-- -- 1) 추가/수정만 가능
-- -- 2) 삭제 않하고 사용여부만 관리
-- -- 사용않하는 레코드는 향후에 배치잡으로 일괄 삭제
-- -- 상품테이블
-- CREATE TABLE TB_PRODUCT
-- (
--     PNO         NUMBER NOT NULL
--         CONSTRAINT PK_PRODUCT PRIMARY KEY, -- 상품번호
--     KIND_CODE   NUMBER,                    -- 상품종류코드
--     PNAME       VARCHAR2(255),             -- 상품명
--     IMAGE       VARCHAR2(255),             -- 이미지 경로
--     UNIT_PRICE  NUMBER,                    -- 단가
--     STATUS_CODE NUMBER DEFAULT 20001,      -- 상품상태코드(20001(신상), 20002(이월상품), 20003(전시품))
--     USE_YN      VARCHAR2(1) DEFAULT 'Y'    -- 사용여부
-- );
--
-- -- 장바구니 테이블
-- CREATE TABLE TB_CART
-- (
--     CNO         NUMBER NOT NULL
--         CONSTRAINT PK_CART PRIMARY KEY,                         -- 장바구니번호
--     PNO         NUMBER
--         CONSTRAINT FK_PRODUCT_CART REFERENCES TB_PRODUCT (PNO), -- 상품번호
--     AMOUNT      NUMBER DEFAULT 0,                               -- 장바구니 상품개수
--     DELETE_YN   VARCHAR2(1) DEFAULT 'N',
--     INSERT_TIME VARCHAR2(255),
--     UPDATE_TIME VARCHAR2(255),
--     DELETE_TIME VARCHAR2(255)
-- );
--
--
-- -- Upload Table
-- CREATE TABLE TB_FILE_DB
-- (
--     UUID         VARCHAR2(1000) NOT NULL
--         CONSTRAINT PK_FILE_DB PRIMARY KEY, -- 파일 UUID
--     FILE_TITLE   VARCHAR2(1000),           -- 제목
--     FILE_CONTENT VARCHAR2(1000),           -- 내용
--     FILE_NAME    VARCHAR2(1000),           -- 파일명
--     FILE_DATA    BLOB,                     -- 바이너리 파일(이미지파일)
--     FILE_URL     VARCHAR2(1000),           -- 파일 다운로드 URL
--     DELETE_YN    VARCHAR2(1) DEFAULT 'N',
--     INSERT_TIME  VARCHAR2(255),
--     UPDATE_TIME  VARCHAR2(255),
--     DELETE_TIME  VARCHAR2(255)
-- );
--
-- -- Upload Gallery Table
-- CREATE TABLE TB_GALLERY
-- (
--     UUID              VARCHAR2(1000) NOT NULL
--         CONSTRAINT PK_GALLERY PRIMARY KEY, -- 파일 UUID
--     GALLERY_TITLE     VARCHAR2(1000),      -- 제목
--     GALLERY_FILE_NAME VARCHAR2(1000),      -- 파일명
--     GALLERY_DATA      BLOB,                -- 바이너리 파일(이미지파일)
--     GALLERY_FILE_URL  VARCHAR2(1000),      -- 파일 다운로드 URL
--     DELETE_YN         VARCHAR2(1) DEFAULT 'N',
--     INSERT_TIME       VARCHAR2(255),
--     UPDATE_TIME       VARCHAR2(255),
--     DELETE_TIME       VARCHAR2(255)
-- );
--
-- -- Table , 시퀀스 등 구조 정의
-- DROP SEQUENCE SQ_DEPT;
-- CREATE SEQUENCE SQ_DEPT START WITH 50 INCREMENT BY 10;
--
-- DROP SEQUENCE SQ_EMP;
-- CREATE SEQUENCE SQ_EMP START WITH 8000 INCREMENT BY 1;
--
-- DROP SEQUENCE SQ_QNA;
-- CREATE SEQUENCE SQ_QNA START WITH 1 INCREMENT BY 1;
--
-- DROP SEQUENCE SQ_CUSTOMER;
-- CREATE SEQUENCE SQ_CUSTOMER START WITH 1 INCREMENT BY 1;
--
-- DROP SEQUENCE SQ_FAQ;
-- CREATE SEQUENCE SQ_FAQ START WITH 1 INCREMENT BY 1;
--
-- DROP SEQUENCE SQ_CINEMA_FAQ;
-- CREATE SEQUENCE SQ_CINEMA_FAQ START WITH 1 INCREMENT BY 1;
--
-- DROP SEQUENCE SQ_REPLY_BOARD;
-- CREATE SEQUENCE SQ_REPLY_BOARD INCREMENT BY 1 START WITH 1;
-- DROP SEQUENCE SQ_THREAD_BOARD;
-- CREATE SEQUENCE SQ_THREAD_BOARD INCREMENT BY 1 START WITH 1;
--
-- DROP SEQUENCE SQ_SIMPLE_PRODUCT;
-- CREATE SEQUENCE SQ_SIMPLE_PRODUCT START WITH 1 INCREMENT BY 1;
--
-- DROP SEQUENCE SQ_SIMPLE_CART;
-- CREATE SEQUENCE SQ_SIMPLE_CART START WITH 1 INCREMENT BY 1;
--
-- DROP SEQUENCE SQ_PRODUCT;
-- CREATE SEQUENCE SQ_PRODUCT START WITH 1 INCREMENT BY 1;
--
-- DROP SEQUENCE SQ_CART;
-- CREATE SEQUENCE SQ_CART START WITH 1 INCREMENT BY 1;
--
-- DROP TABLE TB_EMP CASCADE CONSTRAINT;
-- DROP TABLE TB_DEPT CASCADE CONSTRAINT;
--
-- -- 공통코드 테이블은 시퀀스는 사용하지 않음
-- -- 공통코드 테이블의 등록된 코드는 향후에 않쓰이더라도 삭제/수정하지 않음 : 데이터가 많지않아 오버헤드가 없음
-- DROP TABLE TB_CODE_CATEGORY CASCADE CONSTRAINT;
-- DROP TABLE TB_CODE CASCADE CONSTRAINT;
--
-- DROP TABLE TB_QNA CASCADE CONSTRAINT;
-- DROP TABLE TB_CUSTOMER CASCADE CONSTRAINT;
--
-- DROP TABLE TB_FAQ CASCADE CONSTRAINT;
-- DROP TABLE TB_CINEMA_FAQ CASCADE CONSTRAINT;
--
-- DROP TABLE TB_REPLY_BOARD CASCADE CONSTRAINT;
-- DROP TABLE TB_THREAD_BOARD CASCADE CONSTRAINT;
--
-- DROP TABLE TB_SIMPLE_PRODUCT CASCADE CONSTRAINT;
-- DROP TABLE TB_SIMPLE_CART CASCADE CONSTRAINT;
-- DROP TABLE TB_PRODUCT CASCADE CONSTRAINT;
-- DROP TABLE TB_CART CASCADE CONSTRAINT;
--
-- DROP TABLE TB_FILE_DB CASCADE CONSTRAINT;
-- DROP TABLE TB_GALLERY CASCADE CONSTRAINT;
--
-- -- 부서 테이블
-- CREATE TABLE TB_DEPT
-- (
--     DNO         NUMBER NOT NULL PRIMARY KEY,
--     DNAME       VARCHAR2(255),
--     LOC         VARCHAR2(255),
--     DELETE_YN   VARCHAR2(1) DEFAULT 'N',
--     INSERT_TIME VARCHAR2(255),
--     UPDATE_TIME VARCHAR2(255),
--     DELETE_TIME VARCHAR2(255)
-- );
--
-- -- 사원 테이블 : 문제
-- CREATE TABLE TB_EMP
-- (
--     ENO         NUMBER NOT NULL PRIMARY KEY,
--     ENAME       VARCHAR2(255),
--     JOB         VARCHAR2(255),
--     MANAGER     NUMBER,
--     HIREDATE    VARCHAR2(255),
--     SALARY      NUMBER,
--     COMMISSION  NUMBER,
--     DNO         NUMBER,
--     DELETE_YN   VARCHAR2(1) DEFAULT 'N',
--     INSERT_TIME VARCHAR2(255),
--     UPDATE_TIME VARCHAR2(255),
--     DELETE_TIME VARCHAR2(255)
-- );
--
-- -- 코드성 테이블 : 공통 코드 유형 테이블
-- CREATE TABLE TB_CODE_CATEGORY
-- (
--     CATEGORY_ID   NUMBER NOT NULL
--         CONSTRAINT PK_CODE_CATEGORY PRIMARY KEY,
--     CATEGORY_NAME VARCHAR2(255)
-- );
--
-- -- 코드성 테이블 : 공통 코드 테이블
-- CREATE TABLE TB_CODE
-- (
--     CODE_ID     NUMBER NOT NULL
--         CONSTRAINT PK_CODE PRIMARY KEY,
--     CODE_NAME   VARCHAR2(255),
--     CATEGORY_ID NUMBER NOT NULL
--         CONSTRAINT FK_CODE_CATEGORY_CODE REFERENCES TB_CODE_CATEGORY (CATEGORY_ID),
--     USE_YN      VARCHAR(1) DEFAULT 'Y'
-- );
--
-- -- qna
-- CREATE TABLE TB_QNA
-- (
--     QNO         NUMBER NOT NULL
--         CONSTRAINT PK_QNA PRIMARY KEY,
--     QUESTION    VARCHAR2(255),
--     ANSWER      VARCHAR2(255),
--     QUESTIONER  VARCHAR2(255),
--     ANSWERER    VARCHAR2(255),
--     DELETE_YN   VARCHAR2(1) DEFAULT 'N',
--     INSERT_TIME VARCHAR2(255),
--     UPDATE_TIME VARCHAR2(255),
--     DELETE_TIME VARCHAR2(255)
-- );
--
-- -- 고객 테이블 : 문제
-- CREATE TABLE TB_CUSTOMER
-- (
--     CID         NUMBER NOT NULL
--         CONSTRAINT PK_CUSTOMER PRIMARY KEY,
--     FULL_NAME   VARCHAR2(255),
--     EMAIL       VARCHAR2(255),
--     PHONE       VARCHAR2(255),
--     DELETE_YN   VARCHAR2(1) DEFAULT 'N',
--     INSERT_TIME VARCHAR2(255),
--     UPDATE_TIME VARCHAR2(255),
--     DELETE_TIME VARCHAR2(255)
-- );
--
-- -- faq 테이블
-- CREATE TABLE TB_FAQ
-- (
--     NO          NUMBER NOT NULL
--         CONSTRAINT PK_FAQ PRIMARY KEY, -- faq 번호
--     TITLE       VARCHAR2(255),         -- 제목
--     CONTENT     VARCHAR2(255),         -- 내용
--     DELETE_YN   VARCHAR2(1) DEFAULT 'N',
--     INSERT_TIME VARCHAR2(255),
--     UPDATE_TIME VARCHAR2(255),
--     DELETE_TIME VARCHAR2(255)
-- );
--
-- -- 영화 faq 테이블 : 문제
-- CREATE TABLE TB_CINEMA_FAQ
-- (
--     CFNO        NUMBER NOT NULL
--         CONSTRAINT PK_CINEMA_FAQ PRIMARY KEY, -- faq 번호
--     QUESTION    VARCHAR2(255),                -- 제목
--     ANSWER      VARCHAR2(4000),               -- 내용
--     SORT_ORDER  NUMBER DEFAULT 0,             -- 출력순서(숫자가 클수록 먼저 출력됨)
--     DELETE_YN   VARCHAR2(1) DEFAULT 'N',
--     INSERT_TIME VARCHAR2(255),
--     UPDATE_TIME VARCHAR2(255),
--     DELETE_TIME VARCHAR2(255)
-- );
--
-- -- 답변형 게시판
-- CREATE TABLE TB_REPLY_BOARD
-- (
--     BID           NUMBER NOT NULL
--         CONSTRAINT PK_REPLY_BOARD PRIMARY KEY, -- 게시판번호
--     BOARD_TITLE   VARCHAR2(256),               -- 제목
--     BOARD_CONTENT VARCHAR2(255),               -- 내용
--     BOARD_WRITER  VARCHAR2(255),               -- 작성자
--     VIEW_CNT      NUMBER DEFAULT 0,            -- 조회수
--     BOARD_GROUP   NUMBER,                      -- 트리구조 최상위 부모 노드( 부모가 있을 경우 : 부모번호, 없을 경우 : 자신의 게시판번호 )
--     BOARD_PARENT  NUMBER,                      -- 자신의 부모 노드 ( 부모가 있을 경우 : 부모번호, 없을 경우 : 0 )
--     DELETE_YN     VARCHAR2(1) DEFAULT 'N',
--     INSERT_TIME   VARCHAR2(255),
--     UPDATE_TIME   VARCHAR2(255),
--     DELETE_TIME   VARCHAR2(255)
-- );
--
-- -- 답변형 게시판 : 문제
-- CREATE TABLE TB_THREAD_BOARD
-- (
--     TID         NUMBER NOT NULL
--         CONSTRAINT PK_THREAD_BOARD PRIMARY KEY, -- 게시판번호
--     SUBJECT     VARCHAR2(256),                  -- 제목
--     MAIN_TEXT   VARCHAR2(255),                  -- 내용
--     WRITER      VARCHAR2(255),                  -- 작성자
--     VIEWS       NUMBER DEFAULT 0,               -- 조회수
--     TGROUP      NUMBER,                         -- 트리구조 최상위 부모 노드( 부모가 있을 경우 : 부모번호, 없을 경우 : 자신의 게시판번호 )
--     TPARENT     NUMBER,                         -- 자신의 부모 노드 ( 부모가 있을 경우 : 부모번호, 없을 경우 : 0 )
--     DELETE_YN   VARCHAR2(1) DEFAULT 'N',
--     INSERT_TIME VARCHAR2(255),
--     UPDATE_TIME VARCHAR2(255),
--     DELETE_TIME VARCHAR2(255)
-- );
--
-- -- 마스터성 테이블 : 점이력 관리 않함
-- -- 1) 추가/수정만 가능
-- -- 2) 삭제 않하고 사용여부만 관리
-- -- 사용않하는 레코드는 향후에 배치잡으로 일괄 삭제
-- -- 심픔 상품 테이블
-- CREATE TABLE TB_SIMPLE_PRODUCT
-- (
--     SPNO       NUMBER NOT NULL
--         CONSTRAINT PK_SIMPLE_PRODUCT PRIMARY KEY, -- 상품번호
--     CODE_ID    NUMBER,                            -- 상품종류코드
--     TITLE      VARCHAR2(255),                     -- 상품명
--     IMG_PATH   VARCHAR2(255),                     -- 이미지 경로
--     UNIT_PRICE NUMBER,                            -- 단가
--     USE_YN     VARCHAR2(1) DEFAULT 'Y'            -- 사용여부
-- );
--
-- -- 심플 장바구니 테이블
-- CREATE TABLE TB_SIMPLE_CART
-- (
--     SCNO        NUMBER NOT NULL
--         CONSTRAINT PK_SIMPLE_CART PRIMARY KEY,                                 -- 장바구니번호
--     SPNO        NUMBER
--         CONSTRAINT FK_SIMPLE_PRODUCT_CART REFERENCES TB_SIMPLE_PRODUCT (SPNO), -- 상품번호
--     CART_COUNT  NUMBER DEFAULT 0,                                              -- 장바구니 상품개수
--     DELETE_YN   VARCHAR2(1) DEFAULT 'N',
--     INSERT_TIME VARCHAR2(255),
--     UPDATE_TIME VARCHAR2(255),
--     DELETE_TIME VARCHAR2(255)
-- );
--
-- -- 마스터성 테이블 : 점이력 관리 않함
-- -- 1) 추가/수정만 가능
-- -- 2) 삭제 않하고 사용여부만 관리
-- -- 사용않하는 레코드는 향후에 배치잡으로 일괄 삭제
-- -- 상품테이블
-- CREATE TABLE TB_PRODUCT
-- (
--     PNO         NUMBER NOT NULL
--         CONSTRAINT PK_PRODUCT PRIMARY KEY, -- 상품번호
--     KIND_CODE   NUMBER,                    -- 상품종류코드
--     PNAME       VARCHAR2(255),             -- 상품명
--     IMAGE       VARCHAR2(255),             -- 이미지 경로
--     UNIT_PRICE  NUMBER,                    -- 단가
--     STATUS_CODE NUMBER DEFAULT 20001,      -- 상품상태코드(20001(신상), 20002(이월상품), 20003(전시품))
--     USE_YN      VARCHAR2(1) DEFAULT 'Y'    -- 사용여부
-- );
--
-- -- 장바구니 테이블
-- CREATE TABLE TB_CART
-- (
--     CNO         NUMBER NOT NULL
--         CONSTRAINT PK_CART PRIMARY KEY,                         -- 장바구니번호
--     PNO         NUMBER
--         CONSTRAINT FK_PRODUCT_CART REFERENCES TB_PRODUCT (PNO), -- 상품번호
--     AMOUNT      NUMBER DEFAULT 0,                               -- 장바구니 상품개수
--     DELETE_YN   VARCHAR2(1) DEFAULT 'N',
--     INSERT_TIME VARCHAR2(255),
--     UPDATE_TIME VARCHAR2(255),
--     DELETE_TIME VARCHAR2(255)
-- );
--
--
-- -- Upload Table
-- CREATE TABLE TB_FILE_DB
-- (
--     UUID         VARCHAR2(1000) NOT NULL
--         CONSTRAINT PK_FILE_DB PRIMARY KEY, -- 파일 UUID
--     FILE_TITLE   VARCHAR2(1000),           -- 제목
--     FILE_CONTENT VARCHAR2(1000),           -- 내용
--     FILE_NAME    VARCHAR2(1000),           -- 파일명
--     FILE_DATA    BLOB,                     -- 바이너리 파일(이미지파일)
--     FILE_URL     VARCHAR2(1000),           -- 파일 다운로드 URL
--     DELETE_YN    VARCHAR2(1) DEFAULT 'N',
--     INSERT_TIME  VARCHAR2(255),
--     UPDATE_TIME  VARCHAR2(255),
--     DELETE_TIME  VARCHAR2(255)
-- );
--
-- -- Upload Gallery Table
-- CREATE TABLE TB_GALLERY
-- (
--     UUID              VARCHAR2(1000) NOT NULL
--         CONSTRAINT PK_GALLERY PRIMARY KEY, -- 파일 UUID
--     GALLERY_TITLE     VARCHAR2(1000),      -- 제목
--     GALLERY_FILE_NAME VARCHAR2(1000),      -- 파일명
--     GALLERY_DATA      BLOB,                -- 바이너리 파일(이미지파일)
--     GALLERY_FILE_URL  VARCHAR2(1000),      -- 파일 다운로드 URL
--     DELETE_YN         VARCHAR2(1) DEFAULT 'N',
--     INSERT_TIME       VARCHAR2(255),
--     UPDATE_TIME       VARCHAR2(255),
--     DELETE_TIME       VARCHAR2(255)
-- );
--
-- -- 인증관련 테이블 정의
-- -- 유저 테이블
-- -- login table ddl
-- DROP TABLE TB_USER CASCADE CONSTRAINTS;
--
-- CREATE TABLE TB_USER
-- (
--     EMAIL       VARCHAR2(1000) NOT NULL CONSTRAINT PK_USER PRIMARY KEY, -- id (email)
--     PASSWORD    VARCHAR2(1000),                                         -- 암호
--     USERNAME    VARCHAR2(1000),                                         -- 유저명
--
--
--     CODE_NAME   VARCHAR2(1000),                                         -- 권한코드명(ROLE_USER, ROLE_ADMIN)
--     DELETE_YN   VARCHAR2(1) DEFAULT 'N',
--     INSERT_TIME VARCHAR2(255),
--     UPDATE_TIME VARCHAR2(255),
--     DELETE_TIME VARCHAR2(255)
-- );
