package dev.gabrielgrazziani.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.gabrielgrazziani.model.ItemOrdemServico;
import dev.gabrielgrazziani.model.OrdemServico;
import dev.gabrielgrazziani.model.ServicoProduto;
import dev.gabrielgrazziani.repository.ItemRepository;
import dev.gabrielgrazziani.repository.ServicoProdutoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ItemService {
	
	private final ItemRepository itemRepository;
	private final ServicoProdutoRepository servicoProdutoRepository;
	
	public ItemOrdemServico criar(int quantidade,Long ordemServicoId,Long servicoProdutoId) {
		ServicoProduto servicoProduto = servicoProdutoRepository.findById(servicoProdutoId).get();
		OrdemServico ordemServico = new OrdemServico();
		ordemServico.setId(ordemServicoId);
		
		ItemOrdemServico itemOrdemServico = new ItemOrdemServico();
		itemOrdemServico.setOrdemServico(ordemServico);
		itemOrdemServico.setServicoProduto(servicoProduto);
		itemOrdemServico.setQuantidade(quantidade);
		itemOrdemServico.setValorUnidade(servicoProduto.getValorComercial());
		itemRepository.save(itemOrdemServico);
		return itemOrdemServico;
	}
	
	public List<ItemOrdemServico> list(){
		return itemRepository.findAll();
	}

	public List<ItemOrdemServico> buscaFiltrandoPorUmaOrdemServico(OrdemServico ordemServico) {
		return itemRepository.findByOrdemServico(ordemServico);
	}
}
