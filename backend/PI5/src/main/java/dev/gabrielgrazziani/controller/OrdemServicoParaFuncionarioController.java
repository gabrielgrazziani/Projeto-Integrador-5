package dev.gabrielgrazziani.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gabrielgrazziani.dto.AtualizarOrdensServicoForm;
import dev.gabrielgrazziani.dto.HistoricoResponse;
import dev.gabrielgrazziani.dto.OrdemServicoResponse;
import dev.gabrielgrazziani.dto.PessoaResponse;
import dev.gabrielgrazziani.exceptions.MensException;
import dev.gabrielgrazziani.model.Historico;
import dev.gabrielgrazziani.model.OrdemServico;
import dev.gabrielgrazziani.model.Pessoa;
import dev.gabrielgrazziani.service.OrdemServicoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("funcionario/ordem_servico")
@Api(tags = {"ordem_servico","funcionario"})
public class OrdemServicoParaFuncionarioController {
	
	@Autowired
	private OrdemServicoService ordemServicoService;
	
	@ApiOperation(value = "Busca uma ordem de servico",notes = "Precisa estar logado como FUNCIONARIO")
	@GetMapping("/{id}")
	private OrdemServicoResponse buscarParaFuncionario(
			@ApiParam(value = "id ordem de servico") 
			@PathVariable long id
	) {	
		
		OrdemServico ordemServico = ordemServicoService.busca(id);
		return response(ordemServico);
	}
	
	@ApiResponses({
		@ApiResponse(code = 200,message = "ok",response = OrdemServicoResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "Lista todas as ordens de servicos",notes = "Precisa estar logado como um FUNCIONARIO")
	@GetMapping()
	private List<OrdemServicoResponse> listarParaFuncionario() {	
		return ordemServicoService.lista()
			.stream()
			.map(this::response)
			.collect(Collectors.toList());
	}
	
	@ApiResponses({
		@ApiResponse(code = 200,message = "ok",response = HistoricoResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "Busca historico de uma ordem de servico",notes = "Precisa estar logado como um FUNCIONARIO")
	@GetMapping("/{idOrdemServico}/historico")
	private List<HistoricoResponse> buscarHistoricoParaFuncionario(
			@ApiParam(value = "id ordem de servico") 
			@PathVariable long idOrdemServico) {
		return ordemServicoService.historicoDestaOrdemServico(idOrdemServico)
			.stream()
			.map(this::response)
			.collect(Collectors.toList());
	}
	
	@ApiOperation(value = "Atualizar OrdensServico",notes = "Precisa estar logado como FUNCIONARIO")
	@PatchMapping
	private OrdemServicoResponse atualizarOrdensServico(
			@Valid @RequestBody AtualizarOrdensServicoForm form) {	
		OrdemServico atualizado = ordemServicoService.atualizar(form.getIdOrdensServico(),form.getStatus(),form.getIdFuncionario());
		return response(atualizado);
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
	
	private OrdemServicoResponse response(OrdemServico ordemServico) {
		if(ordemServico == null) return null;
		OrdemServicoResponse ordemServicoResponse = new OrdemServicoResponse();
		BeanUtils.copyProperties(ordemServico,ordemServicoResponse);
		ordemServicoResponse.setCliente(response(ordemServico.getCliente()));
		ordemServicoResponse.setFuncionario(response(ordemServico.getFuncionario()));
		return ordemServicoResponse;
	}
}
