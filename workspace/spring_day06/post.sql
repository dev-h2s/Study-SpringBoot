CREATE SEQUENCE SEQ_POST;

CREATE TABLE TBL_POST(
	ID NUMBER CONSTRAINT PK_POST PRIMARY KEY,
	POST_TITLE VARCHAR2(1000) NOT NULL,
	POST_CONTENT VARCHAR2(1000) NOT NULL,
	POST_REGISTER_DATE DATE DEFAULT SYSDATE,
	POST_UPDATE_DATE DATE DEFAULT SYSDATE,
	POST_READ_COUNT NUMBER DEFAULT 0,
	MEMBER_ID NUMBER NOT NULL,
	CONSTRAINT FK_POST_MEMBER FOREIGN KEY(MEMBER_ID)
	REFERENCES TBL_MEMBER(ID)
);

SELECT * FROM TBL_POST;

