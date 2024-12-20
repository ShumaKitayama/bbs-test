CREATE TABLE IF NOT EXISTS USER_COMMENT (
                                            ID NUMBER(10) AUTO_INCREMENT, -- 主キーとしてふさわしいものがないのでIDを採番
                                            USER_ID VARCHAR2(50) NOT NULL,
                                            NAME VARCHAR2(20),
                                            MAILADDRESS VARCHAR2(100),
                                            TEXT VARCHAR2(400) NOT NULL,  -- NULLを許容しない
                                            CREATED_AT DATE DEFAULT NOW() NOT NULL,
                                            CONSTRAINT ID_PKC PRIMARY KEY(ID) -- IDをプライマリキーとする。(プライマリキーの名前をID_PKCとする)
);