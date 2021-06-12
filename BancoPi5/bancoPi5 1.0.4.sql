create table historico(
 id_historico integer primary key,
 data_historico date ,
 status varchar ,
 ordemServico integer ,
 funcionario integer
);

Alter table public.historico
add foreign key(ordemServico)
references public.ordemservico(ordem_id);

Alter table public.historico
add foreign key(funcionario)
references public.pessoa(pessoa_id);