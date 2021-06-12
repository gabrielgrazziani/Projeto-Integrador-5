-- This script was generated by a beta version of the ERD tool in pgAdmin 4.
-- Please log an issue at https://redmine.postgresql.org/projects/pgadmin4/issues/new if you find any bugs, including reproduction steps.
BEGIN;


CREATE TABLE public.itensordemservico
(
    iten_ord_serv_id integer NOT NULL,
    ordemservico integer NOT NULL,
    sevicoprduto integer NOT NULL,
    quantidade double precision NOT NULL,
    valorunit double precision NOT NULL,
    PRIMARY KEY (iten_ord_serv_id)
);

CREATE TABLE public.ordemservico
(
    ord_ser_id integer NOT NULL,
    funcionario integer NOT NULL,
    cliente integer NOT NULL,
    dataemissao date NOT NULL,
    datafechamento date,
    status character varying(100),
    descricao integer,
    PRIMARY KEY (ord_ser_id)
);

CREATE TABLE public.pessoa
(
    pessoa_id integer NOT NULL,
    pessoa_nome character varying(100) NOT NULL,
    cpfcnpj character varying(100) NOT NULL,
    telefone character varying(100) NOT NULL,
    email character varying(100) NOT NULL,
    funcao character varying(100),
    login character varying(100) NOT NULL,
    senha character varying(100) NOT NULL,
    perfil character varying(100) NOT NULL,
    PRIMARY KEY (pessoa_id)
);

CREATE TABLE public.servicoproduto
(
    id_servicoproduto integer NOT NULL,
    nome character varying(100) NOT NULL,
    tipo character varying(100) NOT NULL,
    valor_custo real NOT NULL,
    valor_comercial real NOT NULL,
    unidade character varying(100) NOT NULL,
    url_imagem character varying(200),
    PRIMARY KEY (id_servicoproduto)
);

ALTER TABLE public.itensordemservico
    ADD FOREIGN KEY (ordemservico)
    REFERENCES public.ordemservico (ord_ser_id)
    NOT VALID;


ALTER TABLE public.itensordemservico
    ADD FOREIGN KEY (sevicoprduto)
    REFERENCES public.servicoproduto (id_servicoproduto)
    NOT VALID;


ALTER TABLE public.ordemservico
    ADD FOREIGN KEY (cliente)
    REFERENCES public.pessoa (pessoa_id)
    NOT VALID;


ALTER TABLE public.ordemservico
    ADD FOREIGN KEY (descricao)
    REFERENCES public.itensordemservico (iten_ord_serv_id)
    NOT VALID;


ALTER TABLE public.ordemservico
    ADD FOREIGN KEY (funcionario)
    REFERENCES public.pessoa (pessoa_id)
    NOT VALID;

END;