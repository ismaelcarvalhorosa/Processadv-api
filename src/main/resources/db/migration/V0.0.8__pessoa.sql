CREATE SEQUENCE public.seq_pessoa;
CREATE TABLE public.pessoa
(
    pescodigo integer NOT NULL,
    pesnome character varying(100) COLLATE pg_catalog."default" NOT NULL,
    pesdatanas date,
    pescpf character varying(11) COLLATE pg_catalog."default" NOT NULL,
    pestelefone character varying(20) COLLATE pg_catalog."default",
    pescelular character varying(20) COLLATE pg_catalog."default",
    pesemail character varying(100) COLLATE pg_catalog."default",
    usucodigo integer NOT NULL,
    pesdatalt date NOT NULL,
    pessexo smallint NOT NULL,
    CONSTRAINT pessoa_pkey PRIMARY KEY (pescodigo),
    CONSTRAINT fk_usupes FOREIGN KEY (usucodigo)
        REFERENCES public.usuario (usucodigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.pessoa
    OWNER to postgres;