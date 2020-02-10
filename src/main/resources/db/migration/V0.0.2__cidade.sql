CREATE SEQUENCE public.seq_cidade;
CREATE TABLE public.cidade
(
    cidcodigo integer NOT NULL,
    cidnome character varying(100) COLLATE pg_catalog."default" NOT NULL,
    ciduf character varying(2) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT cidade_pkey PRIMARY KEY (cidcodigo)
)