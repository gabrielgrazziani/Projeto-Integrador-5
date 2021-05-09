package dev.gabrielgrazziani.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.gabrielgrazziani.model.ServicoProduto;
import dev.gabrielgrazziani.repository.ServicoProdutoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ServicoProdutoService {
	
	private final ServicoProdutoRepository servicoProdutoRepository;
	
	public ServicoProduto busca(long id) {
		return servicoProdutoRepository.findById(id)
				.orElseThrow(() -> notFound());
	}
	
	public List<ServicoProduto> listar(){
		return servicoProdutoRepository.findAll();
	}
	
	private ResponseStatusException notFound() {
		return new ResponseStatusException(HttpStatus.NOT_FOUND, "Servico/Produto não encontrado");
	}

}
