BEGIN;


CREATE TABLE public.historico
(
    id_historico integer NOT NULL,
    data_historico date,
    status character varying,
    ordemservico integer,
    funcionario integer,
    PRIMARY KEY (id_historico)
);

CREATE TABLE public.itensordemservico
(
    id_itensordemservico integer NOT NULL,
    quantidade real NOT NULL,
    valorunit real NOT NULL,
    ordemservico integer NOT NULL,
    servicoprodurto integer,
    PRIMARY KEY (id_itensordemservico)
);

CREATE TABLE public.ordemservico
(
    ordem_id integer NOT NULL,
    funcionario_id integer,
    cliente_id integer NOT NULL,
    data_emissao date NOT NULL,
    data_fechamento date NOT NULL,
    status character varying(100) NOT NULL,
    descricaco character varying(300) NOT NULL,
    PRIMARY KEY (ordem_id)
);

CREATE TABLE public.pessoa
(
    pessoa_id integer NOT NULL,
    pessoa_nome character varying(100) NOT NULL,
    cpfcnpj character varying(100) NOT NULL,
    telefone character varying(100) NOT NULL,
    email character varying(100) NOT NULL,
    funcao character varying(100) NOT NULL,
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

ALTER TABLE public.historico
    ADD FOREIGN KEY (ordemservico)
    REFERENCES public.ordemservico (ordem_id)
    NOT VALID;


ALTER TABLE public.itensordemservico
    ADD FOREIGN KEY (ordemservico)
    REFERENCES public.ordemservico (ordem_id)
    NOT VALID;


ALTER TABLE public.itensordemservico
    ADD FOREIGN KEY (servicoprodurto)
    REFERENCES public.servicoproduto (id_servicoproduto)
    NOT VALID;


ALTER TABLE public.ordemservico
    ADD FOREIGN KEY (cliente_id)
    REFERENCES public.pessoa (pessoa_id)
    NOT VALID;


ALTER TABLE public.ordemservico
    ADD FOREIGN KEY (funcionario_id)
    REFERENCES public.pessoa (pessoa_id)
    NOT VALID;

END;