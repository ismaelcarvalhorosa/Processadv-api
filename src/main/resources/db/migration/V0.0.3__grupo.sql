CREATE SEQUENCE public.seq_grupo;
CREATE TABLE public.grupo
(
    grucodigo integer NOT NULL,
    grunome character varying(100) COLLATE pg_catalog."default" NOT NULL,
	gruidade character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT grupo_pkey PRIMARY KEY (grucodigo)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.grupo
    OWNER to postgres;