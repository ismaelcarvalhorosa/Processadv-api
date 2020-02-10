CREATE SEQUENCE public.seq_comandante;
CREATE TABLE public.comandante
(
    comcodigo integer NOT NULL,
    pescodigo integer NOT NULL,
    cidcodigo integer NOT NULL,
    comendereco character varying(100) COLLATE pg_catalog."default",
    comnumero character varying(5) COLLATE pg_catalog."default",
    combairro character varying(50) COLLATE pg_catalog."default",
    comcomplemento character varying(100) COLLATE pg_catalog."default",
    descodigo integer NOT NULL,
    grucodigo integer,
    comdatini timestamp without time zone NOT NULL,
    comdatfim timestamp without time zone,
    usucodigo integer NOT NULL,
    comdatalt timestamp without time zone NOT NULL,
    CONSTRAINT comandante_pkey PRIMARY KEY (comcodigo),
    CONSTRAINT fk_cidcom FOREIGN KEY (cidcodigo)
        REFERENCES public.cidade (cidcodigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_descom FOREIGN KEY (descodigo)
        REFERENCES public.destacamento (descodigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_grucom FOREIGN KEY (grucodigo)
        REFERENCES public.grupo (grucodigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_pescom FOREIGN KEY (pescodigo)
        REFERENCES public.pessoa (pescodigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_usucom FOREIGN KEY (usucodigo)
        REFERENCES public.usuario (usucodigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.comandante
    OWNER to postgres;