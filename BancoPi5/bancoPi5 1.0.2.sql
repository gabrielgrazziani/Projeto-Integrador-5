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

