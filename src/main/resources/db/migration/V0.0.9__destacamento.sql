CREATE SEQUENCE public.seq_destacamento;
CREATE TABLE public.destacamento
(
    descodigo integer NOT NULL,
    desnome character varying(100) COLLATE pg_catalog."default",
    discodigo integer NOT NULL,
    comcodigo integer,
    desendereco character varying(100) COLLATE pg_catalog."default",
    desnumero character varying(5) COLLATE pg_catalog."default",
    desbairro character varying(50) COLLATE pg_catalog."default",
    descomplemento character varying(20) COLLATE pg_catalog."default",
    cidcodigo integer NOT NULL,
    descep character varying(8) COLLATE pg_catalog."default",
    destelefone character varying(20) COLLATE pg_catalog."default",
    desemail character varying(100) COLLATE pg_catalog."default",
    dessite character varying(100) COLLATE pg_catalog."default",
    desfacebook character varying(100) COLLATE pg_catalog."default",
    desinstagram character varying(100) COLLATE pg_catalog."default",
    desdiapag smallint,
    desvalormensal numeric,
    desdatalt date NOT NULL,
    usucodigo integer NOT NULL,
	igrcodigo integer NOT NULL,
    CONSTRAINT destacamento_pkey PRIMARY KEY (descodigo),
    CONSTRAINT fk_ciddes FOREIGN KEY (cidcodigo)
        REFERENCES public.cidade (cidcodigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_disdes FOREIGN KEY (discodigo)
        REFERENCES public.distrito (discodigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_usudes FOREIGN KEY (usucodigo)
        REFERENCES public.usuario (usucodigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_igrdes FOREIGN KEY (igrcodigo)
        REFERENCES public.igreja (igrcodigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.destacamento
    OWNER to postgres;