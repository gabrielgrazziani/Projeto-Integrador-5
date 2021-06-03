package dev.gabrielgrazziani.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.gabrielgrazziani.dto.OrdemServicoForm;
import dev.gabrielgrazziani.model.Historico;
import dev.gabrielgrazziani.model.OrdemServico;
import dev.gabrielgrazziani.model.Pessoa;
import dev.gabrielgrazziani.model.Status;
import dev.gabrielgrazziani.repository.HistoricoRepository;
import dev.gabrielgrazziani.repository.OrdemServicoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrdemServicoService {

	private final OrdemServicoRepository ordemServicoRepository;
	private final FuncionarioService funcionarioService;
	private final ItemService itemService;
	private final HistoricoRepository historicoRepository;

	public OrdemServico solicitar(OrdemServicoForm form, Pessoa cliente) {
		OrdemServico ordemServico = new OrdemServico();
		ordemServico.setCliente(cliente);
		ordemServico.setDataEmissao(LocalDate.now());
		ordemServico.setDescricao(form.getDescricao());
		ordemServico.setStatus(Status.ABERTO);
		
		ordemServicoRepository.save(ordemServico);
		
		novoHistorico(ordemServico);
		
		form.getItems()
			.forEach(i -> {
				itemService.criar(i.getQuantidade(),ordemServico.getId(),i.getIdServicoProduto());
			});
		
		return ordemServico;
	}


	public OrdemServico busca(long id) {
		return ordemServicoRepository.findById(id)
				.orElseThrow(this::notFound);
	}


	public List<OrdemServico> lista() {
		return ordemServicoRepository.findAll();
	}


	public OrdemServico atualizar(Long idOrdensServico, Status status, Long idFuncionario) {
		OrdemServico ordemServico = busca(idOrdensServico);
		ordemServico.setStatus(status);
		Pessoa funcionario = funcionarioService.busca(idFuncionario);
		ordemServico.setFuncionario(funcionario);
		ordemServicoRepository.save(ordemServico);
		
		novoHistorico(ordemServico,funcionario,status);
		return ordemServico;
	}


	public List<Historico> historicoDestaOrdemServico(Long idOrdemServico) {
		return historicoRepository.findAll()
				.stream()
				.filter(h -> h.getOrdemServico().getId().equals(idOrdemServico))
				.collect(Collectors.toList());
	}


	public OrdemServico buscaFiltrandoPorUmaPessoa(Pessoa pessoa,long id) {
		return ordemServicoRepository.findById(id)
				.filter(e -> e.getCliente().equals(pessoa))
				.orElseThrow(this::notFound);
	}
	
	public List<OrdemServico> listaFiltrandoPorUmaPessoa(Pessoa pessoa) {
		return ordemServicoRepository
				.findAll()
				.stream()
				.filter(e -> e.getCliente().equals(pessoa))
				.collect(Collectors.toList());
	}
	
	public OrdemServico atualizarFiltrandoPorUmaPessoa(Pessoa pessoa,Long idOrdensServico, Status status, Long idFuncionario) {
		OrdemServico ordemServico = buscaFiltrandoPorUmaPessoa(pessoa,idOrdensServico);
		ordemServico.setStatus(status);
		Pessoa funcionario = funcionarioService.busca(idFuncionario);
		ordemServico.setFuncionario(funcionario);
		ordemServicoRepository.save(ordemServico);
		
		novoHistorico(ordemServico,funcionario,status);
		return ordemServico;
	}
	
	public List<Historico> historicoDestaOrdemServicoFiltrandoPorUmaPessoa(Pessoa cliente,Long idOrdemServico) {
		return historicoRepository.findAll()
				.stream()
				.filter(h -> h.getOrdemServico().getId().equals(idOrdemServico))
				.filter(e -> e.getOrdemServico().getCliente().equals(cliente))
				.collect(Collectors.toList());
	}
	
	private void novoHistorico(OrdemServico ordemServico) {
		Historico historico = new Historico(ordemServico, null, Status.ABERTO);
		historicoRepository.save(historico);
	}
	
	private void novoHistorico(OrdemServico ordemServico,Pessoa funcionari,Status status) {
		Historico historico = new Historico(ordemServico, funcionari, status);
		historicoRepository.save(historico);
	}
	
	private ResponseStatusException notFound() {
		return new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado");
	}
}
