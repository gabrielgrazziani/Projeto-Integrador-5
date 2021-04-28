create table servicoProduto(

	id_servicoProduto serial primary key,
	nome varchar(100) not null,
	tipo varchar (100) not null,
	valor_custo float (10) not null,
	valor_comercial float (10) not null,
	unidade varchar (100) not null

);

alter table servicoProduto add column url_imagem varchar(200);

create table Pessoa (
	
	pessoa_id serial not null,
	pessoa_nome varchar(100) not null,
	cpfCnpj varchar(100) not null primary key,
	telefone varchar(100) not null,
	email varchar(100) not null,
	funcao varchar(100),
	login varchar(100) not null,
	senha varchar(100) not null,
	perfil varchar(100) not null
);