CREATE TABLE POST (
    ID           INTEGER NOT NULL,
    IDMUSICO     INTEGER NOT NULL,
	INSTRUMENTO  VARCHAR(50) NOT NULL,
    TITULO       VARCHAR(128) NOT NULL,
	IDIMAGEM	 INTEGER,
    PRIVACIDADE  VARCHAR(128),
    DATA_HORA    TIMESTAMP NOT NULL
);

CREATE UNIQUE INDEX post_idx ON POST (id);

ALTER TABLE POST ADD CONSTRAINT PK_POST PRIMARY KEY (ID);

ALTER TABLE POST ADD CONSTRAINT FK_POST_MUSICO FOREIGN KEY (IDMUSICO) REFERENCES MUSICO;
ALTER TABLE POST ADD CONSTRAINT FK_POST_IMAGEM FOREIGN KEY (IDIMAGEM) REFERENCES IMAGEM;

CREATE SEQUENCE SEQ_POST START WITH 1 INCREMENT BY 1;