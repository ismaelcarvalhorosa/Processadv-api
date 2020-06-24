CREATE SEQUENCE public.seq_cidade_id;
CREATE TABLE public.cidade
(  
    CIDADE_ID       integer NOT NULL,
    CIDADE_NOME     character varying(60) COLLATE pg_catalog."default", 
    CIDADE_UF       character varying(2) COLLATE pg_catalog."default",    
    STATUS          SMALLINT,
    CONSTRAINT CIDADE_pkey PRIMARY KEY (CIDADE_ID)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.CIDADE
    OWNER to postgres;