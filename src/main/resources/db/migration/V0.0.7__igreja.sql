CREATE SEQUENCE public.seq_igreja;
CREATE TABLE public.igreja
(
    igrcodigo integer NOT NULL,
	igrnome character varying(100) COLLATE pg_catalog."default",
	igrpastor character varying(100) COLLATE pg_catalog."default",
	igrpastor2 character varying(100) COLLATE pg_catalog."default",
    igrendereco character varying(100) COLLATE pg_catalog."default",
    igrnumero character varying(5) COLLATE pg_catalog."default",
    igrbairro character varying(50) COLLATE pg_catalog."default",
    igrcomplemento character varying(100) COLLATE pg_catalog."default",
    igrcep character varying(8) COLLATE pg_catalog."default",
    igrcontato character varying(20) COLLATE pg_catalog."default",
    igrcontato2 character varying(20) COLLATE pg_catalog."default",
    igremail character varying(100) COLLATE pg_catalog."default",
    igrsite character varying(100) COLLATE pg_catalog."default",
	cidcodigo integer NOT NULL,
    usucodigo integer NOT NULL,
    igrdatalt date NOT NULL,
    CONSTRAINT igreja_pkey PRIMARY KEY (igrcodigo),
    CONSTRAINT fk_cidigr FOREIGN KEY (cidcodigo)
        REFERENCES public.cidade (cidcodigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_usuigr FOREIGN KEY (usucodigo)
        REFERENCES public.usuario (usucodigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.igreja
    OWNER to postgres;