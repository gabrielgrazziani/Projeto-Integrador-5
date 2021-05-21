package dev.gabrielgrazziani.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gabrielgrazziani.dto.AtualizarOrdensServicoForm;
import dev.gabrielgrazziani.dto.HistoricoResponse;
import dev.gabrielgrazziani.dto.OrdemServicoResponse;
import dev.gabrielgrazziani.exceptions.MensException;
import dev.gabrielgrazziani.model.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("funcionario/ordem_servico")
@Api(tags = {"ordem_servico","funcionario"})
public class OrdemServicoParaFuncionarioController {
	
	@ApiOperation(value = "Busca uma ordem de servico",notes = "Precisa estar logado como FUNCIONARIO")
	@GetMapping("/{id}")
	private OrdemServicoResponse buscarParaFuncionario(
			@ApiParam(value = "id ordem de servico") 
			@PathVariable long id
	) {	
		return OrdemServicoResponse.builder()
			.id(id)
			.descricao("bla bla")
			.status(Status.ABERTO)
			.dataEmissao(LocalDate.now())
			.dataFechamento(null)
//			.idCliente(2L)
//			.idFuncionario(null)
			.build();
	}
	
	@ApiResponses({
		@ApiResponse(code = 200,message = "ok",response = OrdemServicoResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "Lista todas as ordens de servicos",notes = "Precisa estar logado como um FUNCIONARIO")
	@GetMapping()
	private List<OrdemServicoResponse> listarParaFuncionario() {	
		List<OrdemServicoResponse> ordem = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			ordem.add(buscarParaFuncionario(i));
		}
		return ordem;
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
		List<HistoricoResponse> historicos = new ArrayList<>();
		LocalDateTime date = LocalDateTime.now().minusDays(5);
		historicos.add(historico(idOrdemServico,Status.ABERTO,4L,null,date));
		historicos.add(historico(idOrdemServico,Status.EM_EXECUCAO,6L,4L,date.plusDays(1)));
		historicos.add(historico(idOrdemServico,Status.CONCLUIDO,7L,4L,date.plusDays(3)));
		
		return historicos;
	}
	
	@ApiOperation(value = "Atualizar OrdensServico",notes = "Precisa estar logado como FUNCIONARIO")
	@PatchMapping
	private OrdemServicoResponse atualizarOrdensServico(
			@Valid @RequestBody AtualizarOrdensServicoForm form) {	
		return OrdemServicoResponse.builder()
			.id(form.getIdOrdensServico())
			.descricao("bla bla")
			.status(form.getStatus())
			.dataEmissao(LocalDate.now())
			.dataFechamento(null)
//			.idCliente(2L)
//			.idFuncionario(form.getIdFuncionario())
			.build();
	}
	
	private HistoricoResponse historico(Long ordemServico,Status status, Long id, Long funcionario, LocalDateTime date) {
		return HistoricoResponse.builder()
				.id(id)
				.idFuncionario(funcionario)
				.status(status)
				.data(date)
				.idOrdemServico(ordemServico)
				.build();
	}
}
