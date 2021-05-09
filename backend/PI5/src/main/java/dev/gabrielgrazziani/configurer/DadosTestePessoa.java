package dev.gabrielgrazziani.configurer;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import dev.gabrielgrazziani.model.Perfil;
import dev.gabrielgrazziani.model.Pessoa;
import dev.gabrielgrazziani.service.ClienteService;

@Configuration
public class DadosTestePessoa {

	@Autowired
	private ClienteService clienteService;
	
	@PostConstruct
	@Profile("dev")
	@Transactional
	public void criarPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpfCnpj("132.882.170-66");
		pessoa.setEmail("exemplo@email.com");
		pessoa.setNome("cliente");
		pessoa.setSenha("Exemplo$533");
		pessoa.setTelefone("(32)3232-3232");
		
		pessoa.setPerfil(Perfil.CLIENTE);
		
		clienteService.criar(pessoa);
	}
}
