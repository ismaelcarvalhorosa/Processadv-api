CREATE SEQUENCE public.seq_responsavel;
CREATE TABLE public.responsavel
(
    rescodigo integer NOT NULL,
    pescodigo integer NOT NULL,
    descodigo integer NOT NULL,
    cidcodigo integer NOT NULL,
    resendereco character varying(100) COLLATE pg_catalog."default",
    resnumero character varying(5) COLLATE pg_catalog."default",
    resbairro character varying(50) COLLATE pg_catalog."default",
    rescomplemento character varying(100) COLLATE pg_catalog."default",
    resdatini date NOT NULL,
    resdatfim date,
    usucodigo integer NOT NULL,
    resdatalt character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT responsavel_pkey PRIMARY KEY (rescodigo),
    CONSTRAINT fk_cidres FOREIGN KEY (cidcodigo)
        REFERENCES public.cidade (cidcodigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_desres FOREIGN KEY (descodigo)
        REFERENCES public.destacamento (descodigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_pesres FOREIGN KEY (pescodigo)
        REFERENCES public.pessoa (pescodigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_usures FOREIGN KEY (usucodigo)
        REFERENCES public.usuario (usucodigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.responsavel
    OWNER to postgres;