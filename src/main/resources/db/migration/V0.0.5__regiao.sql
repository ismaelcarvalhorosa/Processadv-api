CREATE SEQUENCE public.seq_regiao;
CREATE TABLE public.regiao
(
    regcodigo integer NOT NULL,
    regnome character varying(100) COLLATE pg_catalog."default" NOT NULL,
    regdatalt date NOT NULL,
    usucodigo integer NOT NULL,
    CONSTRAINT regiao_pkey PRIMARY KEY (regcodigo),
    CONSTRAINT fk_usureg FOREIGN KEY (usucodigo)
        REFERENCES public.usuario (usucodigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.regiao
    OWNER to postgres;