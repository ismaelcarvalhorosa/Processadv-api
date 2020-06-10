CREATE SEQUENCE public.seq_cliente_obs;
CREATE TABLE public.CLIENTE_OBS
(
    OBSERVACAO_ID integer NOT NULL,
    CLIENTE_ID integer,
	DATA date,
	STATUS smallint,
	DATA_ATUALIZACAO date,
	OBSERVACAO text,
    CONSTRAINT cliente_obs_pkey PRIMARY KEY (OBSERVACAO_ID)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.CLIENTE_OBS
    OWNER to postgres;