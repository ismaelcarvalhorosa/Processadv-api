CREATE SEQUENCE public.seq_usuarios;
CREATE TABLE public.USUARIOS
(
    USUARIO_ID      integer NOT NULL,
    USUARIO_NOME    character varying(60) COLLATE pg_catalog."default",
    USUARIO_LOGIN   character varying(60) COLLATE pg_catalog."default",
    USUARIO_SENHA   character varying(60) COLLATE pg_catalog."default",
    STATUS          SMALLINT,
    NIVEL_ACESSO    INTEGER,
    COLABORADOR_ID  INTEGER,
    AGENDA          INTEGER,
    USUARIO_LOGO    TEXT,
    CONSTRAINT usuarios_pkey PRIMARY KEY (USUARIO_ID)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.USUARIOS
    OWNER to postgres;