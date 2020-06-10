CREATE SEQUENCE public.seq_cliente;
CREATE TABLE public.CLIENTE
(
    CLIENTE_ID integer NOT NULL,
    NOME character varying(60) COLLATE pg_catalog."default",
	RG character varying(20) COLLATE pg_catalog."default",
	ORGAO character varying(20) COLLATE pg_catalog."default",
	CPF character varying(18) COLLATE pg_catalog."default",
	RAZAO_SOCIAL character varying(80) COLLATE pg_catalog."default",
	ESTADO_CIVIL smallint,
	CONJUGUE character varying(60) COLLATE pg_catalog."default",
	PROFISSAO character varying(60) COLLATE pg_catalog."default",
	PAI character varying(60) COLLATE pg_catalog."default",
	MAE character varying(60) COLLATE pg_catalog."default",
	NIT character varying(60) COLLATE pg_catalog."default",
	APOSENTADO smallint,
	TIPO_APOSENTA smallint,
	NB character varying(60) COLLATE pg_catalog."default",
	DIB date,
	RMI numeric(15,2),
	TC character varying(60) COLLATE pg_catalog."default",
	ENDERECO character varying(80) COLLATE pg_catalog."default",
	END_NRO character varying(10) COLLATE pg_catalog."default",
	BAIRRO character varying(60) COLLATE pg_catalog."default",
	COMPLEMENTO character varying(40) COLLATE pg_catalog."default",
	CEP character varying(10) COLLATE pg_catalog."default",
	EMAIL character varying(50) COLLATE pg_catalog."default",
	TELEFONE character varying(30) COLLATE pg_catalog."default",
	FOTO TEXT,
	DATA_NASCIMENTO date,
	DDB date,
	DER date,
	DCB date,
	APS character varying(100) COLLATE pg_catalog."default",
	ESP character varying(3) COLLATE pg_catalog."default",
	SB decimal(15,2),
	DIP date,
	OP character varying(100) COLLATE pg_catalog."default",
	NACIONALIDADE character varying(60) COLLATE pg_catalog."default",
	MEU_INSS character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT cliente_pkey PRIMARY KEY (cliente_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.CLIENTE
    OWNER to postgres;