CREATE TABLE MUSICO (
    ID              INTEGER NOT NULL,
    NOME_COMPLETO   VARCHAR(255) NOT NULL,
    EMAIL           VARCHAR(255) NOT NULL UNIQUE,
    APELIDO         VARCHAR(50),
    DATA_NASCIMENTO DATE NOT NULL,
    SENHA           VARCHAR(128) NOT NULL,
	IDIMAGEM		INTEGER,
    INSTRUMENTO   	VARCHAR(50),
    PRIVACIDADE     INTEGER CHECK (PRIVACIDADE = 1 or PRIVACIDADE = 2) DEFAULT 1,
    PERFIL          VARCHAR(50) NOT NULL,
    ATIVO           INTEGER NOT NULL
);

CREATE UNIQUE INDEX musico_idx ON MUSICO (id);

ALTER TABLE MUSICO ADD CONSTRAINT PK_MUSICO PRIMARY KEY (ID);

ALTER TABLE MUSICO ADD CONSTRAINT FK_MUSICO_IMAGEM FOREIGN KEY (IDIMAGEM) REFERENCES IMAGEM;

CREATE SEQUENCE SEQ_MUSICO START WITH 1 INCREMENT BY 1;