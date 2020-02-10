CREATE SEQUENCE public.seq_distrito;
CREATE TABLE public.distrito
(
    discodigo integer NOT NULL,
    disnome character varying(100) COLLATE pg_catalog."default",
    regcodigo integer NOT NULL,
    disdatalt date NOT NULL,
    usucodigo integer NOT NULL,
    CONSTRAINT distrito_pkey PRIMARY KEY (discodigo),
    CONSTRAINT fk_usudis FOREIGN KEY (usucodigo)
        REFERENCES public.usuario (usucodigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_regdis FOREIGN KEY (regcodigo)
        REFERENCES public.regiao (regcodigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.distrito
    OWNER to postgres;