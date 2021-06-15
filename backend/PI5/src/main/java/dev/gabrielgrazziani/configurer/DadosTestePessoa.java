package dev.gabrielgrazziani.configurer;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import dev.gabrielgrazziani.model.Perfil;
import dev.gabrielgrazziani.model.Pessoa;
import dev.gabrielgrazziani.repository.PessoaRepository;

@Configuration
@Profile("dev")
public class DadosTestePessoa {

	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private PasswordEncoder encoder;
	
	@PostConstruct
	@Transactional
	public void criarPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpfCnpj("132.882.170-66");
		pessoa.setEmail("exemplo@email.com");
		pessoa.setLogin("exemplo@email.com");
		pessoa.setNome("cliente");
		pessoa.setSenha(encoder.encode("Exemplo$533"));
		pessoa.setTelefone("(32)3232-3232");
		
		pessoa.setPerfil(Perfil.CLIENTE);
		
		pessoaRepository.save(pessoa);
		
		Pessoa pessoa2 = new Pessoa();
		pessoa2.setCpfCnpj("861.805.856-44");
		pessoa2.setEmail("exemplo2@email.com");
		pessoa2.setLogin("exemplo2@email.com");
		pessoa2.setNome("funcionario");
		pessoa2.setSenha(encoder.encode("Exemplo$533"));
		pessoa2.setTelefone("(32)3432-3232");
		
		pessoa2.setPerfil(Perfil.FUNCIONARIO);
		
		pessoaRepository.save(pessoa2);
	}
}
