CREATE TABLE IMAGEM (
    ID             INTEGER NOT NULL,
    URL            VARCHAR(300)
);

CREATE UNIQUE INDEX imagem_idx ON IMAGEM (id);

ALTER TABLE IMAGEM ADD CONSTRAINT PK_IMAGEM PRIMARY KEY (ID);

CREATE SEQUENCE SEQ_IMAGEM START WITH 1 INCREMENT BY 1;