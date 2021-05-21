package dev.gabrielgrazziani.service;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.gabrielgrazziani.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PessoaDetailService implements UserDetailsService{

	private final PessoaRepository repository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return repository.findByEmail(email)
				.orElseThrow(() -> notFound());
	}
	
	private ResponseStatusException notFound() {
		return new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrado");
	}

}
