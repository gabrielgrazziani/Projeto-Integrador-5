package dev.gabrielgrazziani.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.gabrielgrazziani.dto.MudarSenhaForm;
import dev.gabrielgrazziani.model.Perfil;
import dev.gabrielgrazziani.model.Pessoa;
import dev.gabrielgrazziani.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FuncionarioService {

	private final PessoaRepository pessoaRepository;
	private final PasswordEncoder encoder;

	public Pessoa cria(Pessoa pessoa) {
		pessoa.setId(null);
		pessoa.setPerfil(Perfil.FUNCIONARIO);
		pessoa.setSenha(encoder.encode(pessoa.getSenha()));
		pessoaRepository.save(pessoa);
		return pessoa;
	}

	@Transactional
	public Pessoa altera(Pessoa pessoa) {	
		Pessoa pessoaBanco = pessoaRepository.findById(pessoa.getId())
				.filter(e -> e.getPerfil() == Perfil.FUNCIONARIO)
				.orElseThrow(() -> notFound());
		pessoaBanco.setCpfCnpj(pessoa.getCpfCnpj());
		pessoaBanco.setEmail(pessoa.getEmail());
		pessoaBanco.setFuncao(pessoa.getFuncao());
		pessoaBanco.setTelefone(pessoa.getTelefone());
		pessoaBanco.setLogin(pessoa.getEmail());
		pessoaBanco.setNome(pessoa.getNome());
		pessoaRepository.save(pessoaBanco);
		return pessoaBanco;
	}

	public boolean mudarSenha(MudarSenhaForm form) {
		Pessoa pessoa = pessoaRepository.findById(form.getIdPessoa())
				.filter(e -> e.getPerfil() == Perfil.FUNCIONARIO)
				.orElseThrow(() -> notFound());
		boolean match = encoder.matches(form.getAntigaSenha(), pessoa.getSenha());
		if(match) {
			pessoa.setSenha(encoder.encode(form.getNovaSenha()));
			return true;
		}
		return false;
	}

	public Pessoa busca(Long id) {
		return pessoaRepository.findById(id)
			.filter(e -> e.getPerfil() == Perfil.FUNCIONARIO)
			.orElseThrow(() -> notFound());
	}
	
	private ResponseStatusException notFound() {
		return new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionario não encontrado");
	}

	public List<Pessoa> lista() {
		return pessoaRepository
				.findAll()
				.stream()
				.filter(e -> e.getPerfil() == Perfil.FUNCIONARIO)
				.collect(Collectors.toList());
	}

	public void excluir(Long id) {
		Pessoa pessoa = pessoaRepository.findById(id)
			.filter(e -> e.getPerfil() == Perfil.FUNCIONARIO)
			.orElseThrow(() -> notFound());
		pessoaRepository.delete(pessoa);
		
	}
}
