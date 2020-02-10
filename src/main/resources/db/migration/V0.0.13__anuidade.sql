CREATE SEQUENCE public.seq_anuidade;
CREATE TABLE public.anuidade
(
    anucodigo integer NOT NULL,
	descodigo integer NOT NULL,
    expcodigo integer NOT NULL,
	anuano integer NOT NULL,
    anudatemi date NOT NULL,
    anudatven date NOT NULL,
    anuvalor numeric(15,2) NOT NULL,
    anudatpag date,
    anuvalorpago numeric(15,2),
	usucodigo integer NOT NULL,
    CONSTRAINT anuidade_pkey PRIMARY KEY (anucodigo),
    CONSTRAINT fk_expanu FOREIGN KEY (expcodigo)
        REFERENCES public.explorador (expcodigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_desanu FOREIGN KEY (descodigo)
        REFERENCES public.destacamento (descodigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_usuanu FOREIGN KEY (usucodigo)
        REFERENCES public.usuario (usucodigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.anuidade
    OWNER to postgres;