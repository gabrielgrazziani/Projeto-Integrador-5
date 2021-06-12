

create table OrdemServico (

	Ordem_id serial not null primary key,
	funcionario_id int,
	cliente_id int not null,
	data_emissao date not null,
	data_fechamento date not null,
	status varchar (100) not null,
	descricaco varchar(300) not null,
	FOREIGN KEY (funcionario_id) REFERENCES pessoa (pessoa_id),
	FOREIGN KEY (cliente_id) REFERENCES pessoa (pessoa_id)
);




CREATE TABLE public.itensordemservico
(
    id integer NOT NULL,
    ordemservico integer NOT NULL,
    sevicoprduto integer NOT NULL,
    quantidade double precision NOT NULL,
    valorunit double precision NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.ordemservico
(
    id integer NOT NULL,
    funcionario integer NOT NULL,
    cliente integer NOT NULL,
    dataemissao date NOT NULL,
    datafechamento date,
    status character varying(100),
    descricao character varying(500),
    PRIMARY KEY (id)
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
    REFERENCES public.ordemservico (id)
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
    ADD FOREIGN KEY (funcionario)
    REFERENCES public.pessoa (pessoa_id)
    NOT VALID;




