package dev.gabrielgrazziani.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.gabrielgrazziani.dto.HistoricoResponse;
import dev.gabrielgrazziani.dto.ItemResponse;
import dev.gabrielgrazziani.dto.OrdemServicoForm;
import dev.gabrielgrazziani.dto.OrdemServicoResponse;
import dev.gabrielgrazziani.dto.OrdemServicoResponseDetalhado;
import dev.gabrielgrazziani.dto.PessoaResponse;
import dev.gabrielgrazziani.dto.ServicoProdutoResponse;
import dev.gabrielgrazziani.exceptions.MensException;
import dev.gabrielgrazziani.model.Historico;
import dev.gabrielgrazziani.model.ItemOrdemServico;
import dev.gabrielgrazziani.model.OrdemServico;
import dev.gabrielgrazziani.model.Pessoa;
import dev.gabrielgrazziani.model.ServicoProduto;
import dev.gabrielgrazziani.service.ItemService;
import dev.gabrielgrazziani.service.OrdemServicoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("cliente/ordem_servico")
@Api(tags = {"ordem_servico","cliente"})
@CrossOrigin(origins = "http://localhost:4200")
public class OrdemServicoParaClienteController {
	
	private final OrdemServicoService ordemServicoService;
	private final ItemService itemService;
	
	@ApiResponses({
		@ApiResponse(code = 201,message = "created",response = OrdemServicoResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "Cria um uma ordem de servico",notes = "Precisa estar logado como um CLIENTE")
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	private OrdemServicoResponse solicitar(@Valid @RequestBody OrdemServicoForm form,@AuthenticationPrincipal Pessoa cliente) {		
		OrdemServico ordemServico = ordemServicoService.solicitar(form,cliente);
		
		OrdemServicoResponse ordemServicoResponse = response(ordemServico);
		
		return ordemServicoResponse;
	}
	
	@ApiResponses({
		@ApiResponse(code = 200,message = "ok",response = OrdemServicoResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "Lista as ordens de servicos do cliente Logado",notes = "Precisa estar logado como um CLIENTE")
	@GetMapping()
	private List<OrdemServicoResponse> listar(@AuthenticationPrincipal Pessoa pessoa) {	
		return ordemServicoService.listaFiltrandoPorUmaPessoa(pessoa)
				.stream()
				.map(this::response)
				.collect(Collectors.toList());
	}

	@ApiOperation(value = "Busca uma ordem de servico",notes = "Precisa estar logado como um CLIENTE ao qual esta ordem de servico pertence")
	@GetMapping("/{id}")
	private OrdemServicoResponseDetalhado buscar(
			@ApiParam(value = "id ordem de servico") 
			@PathVariable long id,
			@AuthenticationPrincipal Pessoa pessoa
	) {	
		OrdemServico ordemServico = ordemServicoService.buscaFiltrandoPorUmaPessoa(pessoa,id);
		
		List<ItemOrdemServico> items = itemService.buscaFiltrandoPorUmaOrdemServico(ordemServico);
		
		return response(ordemServico,items);
	}

	@ApiResponses({
		@ApiResponse(code = 200,message = "ok",response = HistoricoResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "Busca historico de uma ordem de servico",notes = "Precisa estar logado como um CLIENTE ao qual esta ordem de servico pertence")
	@GetMapping("/{idOrdemServico}/historico")
	private List<HistoricoResponse> buscarHistoricoParaCliente(
			@ApiParam(value = "id ordem de servico") 
			@PathVariable long idOrdemServico,
			@AuthenticationPrincipal Pessoa pessoa
	) {
		return ordemServicoService.historicoDestaOrdemServicoFiltrandoPorUmaPessoa(pessoa,idOrdemServico)
				.stream()
				.map(this::response)
				.collect(Collectors.toList());
	}
	
	private HistoricoResponse response(Historico historico) {
		if(historico == null) return null;
		return HistoricoResponse.builder()
				.id(historico.getId())
				.idFuncionario(responseId(historico.getFuncionario()))
				.status(historico.getStatus())
				.data(historico.getData())
				.idOrdemServico(historico.getOrdemServico().getId())
				.build();
	}
	
	private Long responseId(Pessoa pessoa) {
		if(pessoa == null) return null;
		return pessoa.getId();
	}
	
	
	private PessoaResponse response(Pessoa pessoa) {
		if(pessoa == null) return null;
		PessoaResponse pessoaResponse = new PessoaResponse();
		BeanUtils.copyProperties(pessoa,pessoaResponse);
		return pessoaResponse;
	}
	
	private OrdemServicoResponseDetalhado response(OrdemServico ordemServico,List<ItemOrdemServico> items) {
		if(ordemServico == null) return null;
		OrdemServicoResponseDetalhado ordemServicoResponse = new OrdemServicoResponseDetalhado();
		BeanUtils.copyProperties(ordemServico,ordemServicoResponse);
		ordemServicoResponse.setCliente(response(ordemServico.getCliente()));
		ordemServicoResponse.setFuncionario(response(ordemServico.getFuncionario()));
		
		List<ItemResponse> itemsRespose = items.stream()
			.map(this::response)
			.collect(Collectors.toList());
		ordemServicoResponse.setItems(itemsRespose);
		
		return ordemServicoResponse;
	}
	
	private OrdemServicoResponse response(OrdemServico ordemServico) {
		if(ordemServico == null) return null;
		OrdemServicoResponse ordemServicoResponse = new OrdemServicoResponse();
		BeanUtils.copyProperties(ordemServico,ordemServicoResponse);
		ordemServicoResponse.setCliente(response(ordemServico.getCliente()));
		ordemServicoResponse.setFuncionario(response(ordemServico.getFuncionario()));
		
		return ordemServicoResponse;
	}
	
	private ItemResponse response(ItemOrdemServico item) {
		if(item == null) return null;
		return ItemResponse.builder()
			.id(item.getId())
			.idOrdemServico(item.getOrdemServico().getId())
			.servicoProduto(response(item.getServicoProduto()))
			.quantidade(item.getQuantidade())
			.valorUnidade(item.getValorUnidade())
			.build();
	}
	
	private ServicoProdutoResponse response(ServicoProduto servicoProduto) {
		if(servicoProduto == null) return null;
		ServicoProdutoResponse servicoProdutoResponse = new ServicoProdutoResponse();
		BeanUtils.copyProperties(servicoProduto, servicoProdutoResponse);
		return servicoProdutoResponse;
	}
}
