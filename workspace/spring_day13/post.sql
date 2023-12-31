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

SELECT * FROM TBL_POST WHERE ID = 22;

SELECT * FROM
(
	SELECT ROWNUM R, P.* FROM TBL_MEMBER M JOIN TBL_POST P
	ON M.ID = P.MEMBER_ID AND ROWNUM <= 4
	ORDER BY P.ID DESC
)
WHERE R >= 1;

SELECT * FROM TBL_MEMBER;

INSERT INTO TBL_POST
(ID, POST_TITLE, POST_CONTENT, MEMBER_ID)
(SELECT SEQ_POST.NEXTVAL, POST_TITLE, POST_CONTENT, 21 FROM TBL_POST);


