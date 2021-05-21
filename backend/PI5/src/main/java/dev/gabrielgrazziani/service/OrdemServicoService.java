package dev.gabrielgrazziani.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import dev.gabrielgrazziani.dto.OrdemServicoForm;
import dev.gabrielgrazziani.model.OrdemServico;
import dev.gabrielgrazziani.model.Pessoa;
import dev.gabrielgrazziani.model.Status;
import dev.gabrielgrazziani.repository.OrdemServicoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrdemServicoService {

	private final OrdemServicoRepository ordemServicoRepository;
	private final ItemService itemService;


	public OrdemServico solicitar(OrdemServicoForm form, Pessoa cliente) {
		OrdemServico ordemServico = new OrdemServico();
		ordemServico.setCliente(cliente);
		ordemServico.setDataEmissao(LocalDate.now());
		ordemServico.setDescricao(form.getDescricao());
		ordemServico.setStatus(Status.ABERTO);
		
		ordemServicoRepository.save(ordemServico);
		
		form.getItems()
			.forEach(i -> {
				itemService.criar(i.getQuantidade(),ordemServico.getId(),i.getIdServicoProduto());
			});
		
		return ordemServico;
	}
}
