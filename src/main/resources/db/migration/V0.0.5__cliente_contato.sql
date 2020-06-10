CREATE SEQUENCE public.seq_cliente_contato;
CREATE TABLE public.cliente_contato
(
    CONTATO_ID      integer NOT NULL,
    CLIENTE_ID      integer,
    DESCRICAO       character varying(80) COLLATE pg_catalog."default",
    TELEFONE        character varying(30) COLLATE pg_catalog."default",
    STATUS          SMALLINT,
    CONSTRAINT cliente_contato_pkey PRIMARY KEY (CONTATO_ID)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.cliente_contato
    OWNER to postgres;