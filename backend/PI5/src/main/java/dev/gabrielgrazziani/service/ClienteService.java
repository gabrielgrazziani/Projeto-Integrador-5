package dev.gabrielgrazziani.service;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.gabrielgrazziani.model.Perfil;
import dev.gabrielgrazziani.model.Pessoa;
import dev.gabrielgrazziani.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ClienteService {
	
	private final PessoaRepository pessoaRepository;
	private final PasswordEncoder encoder;
	
	@Transactional
	public Pessoa criar(Pessoa pessoa) {
		pessoa.setId(null);
		pessoa.setPerfil(Perfil.CLIENTE);
		pessoa.setLogin(pessoa.getEmail());
		pessoa.setSenha(encoder.encode(pessoa.getSenha()));
		return pessoaRepository.save(pessoa);
	}
	
}
