CREATE SEQUENCE public.seq_explorador;
CREATE TABLE public.explorador
(
    expcodigo integer NOT NULL,
    pescodigo integer NOT NULL,
    descodigo integer NOT NULL,
    cidcodigo integer NOT NULL,
    expendereco character varying(100) COLLATE pg_catalog."default",
    expnumero character varying(5) COLLATE pg_catalog."default",
    expbairro character varying(50) COLLATE pg_catalog."default",
    expcomplemento character varying(100) COLLATE pg_catalog."default",
    expcep character varying(8) COLLATE pg_catalog."default",
    expdatini date NOT NULL,
    expdatfim date,
    usucodigo integer NOT NULL,
    expdatalt date NOT NULL,
    rescodigo integer NOT NULL,
    CONSTRAINT explorador_pkey PRIMARY KEY (expcodigo),
    CONSTRAINT fk_cidexp FOREIGN KEY (cidcodigo)
        REFERENCES public.cidade (cidcodigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_desexp FOREIGN KEY (descodigo)
        REFERENCES public.destacamento (descodigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_pesexp FOREIGN KEY (pescodigo)
        REFERENCES public.pessoa (pescodigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_resexp FOREIGN KEY (rescodigo)
        REFERENCES public.responsavel (rescodigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_usuexp FOREIGN KEY (usucodigo)
        REFERENCES public.usuario (usucodigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.explorador
    OWNER to postgres;