CREATE SEQUENCE public.seq_usuario;
CREATE TABLE public.usuario
(
    ususenha character varying(100) COLLATE pg_catalog."default" NOT NULL,
    usunome character varying(100) COLLATE pg_catalog."default" NOT NULL,
    usulogin character varying(100) COLLATE pg_catalog."default" NOT NULL,
    usudatalt date NOT NULL,
    usucodigo integer NOT NULL,
    usupermissao smallint NOT NULL,
    usuemail character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT usuario_pkey PRIMARY KEY (usucodigo)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.usuario
    OWNER to postgres;

COMMENT ON COLUMN public.usuario.usucodigo
    IS 'Código';