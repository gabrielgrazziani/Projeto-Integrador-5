package dev.gabrielgrazziani.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.gabrielgrazziani.dto.HistoricoResponse;
import dev.gabrielgrazziani.dto.OrdemServicoForm;
import dev.gabrielgrazziani.dto.OrdemServicoResponse;
import dev.gabrielgrazziani.dto.PessoaResponse;
import dev.gabrielgrazziani.exceptions.MensException;
import dev.gabrielgrazziani.model.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("ordem_servico")
@Api(tags = "ordem_servico")
public class OrdemServicoController {
	
	@ApiResponses({
		@ApiResponse(code = 201,message = "created",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "Cria um uma ordem de servico",notes = "Precisa estar logado como um CLIENTE")
	@PostMapping
	private OrdemServicoResponse solicitar(@Valid @RequestBody OrdemServicoForm form) {	
		return OrdemServicoResponse.builder()
			.id(3L)
			.descricao(form.getDescricao())
			.status(Status.ABERTO)
			.dataEmissao(LocalDate.now())
			.dataFechamento(null)
			.idCliente(1l)
			.idFuncionario(null)
			.build();
	}
	
	@ApiResponses({
		@ApiResponse(code = 201,message = "created",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "Busca uma ordem de servico",notes = "Precisa estar logado como um CLIENTE ao qual esta ordem de servico pertense ou comum FUNCIONARIO qual quer")
	@GetMapping("/{id}")
	private OrdemServicoResponse buscar(
			@ApiParam(value = "id ordem de servico") 
			@PathVariable long id,
			@ApiParam(value = "id CLIENTE, so é obrigatorio se não estiver logado com um CLIENTE") 
			@RequestParam(defaultValue = "0",required = false) long idCliente
	) {	
		if(idCliente < 1) idCliente = 2;
		return OrdemServicoResponse.builder()
			.id(id)
			.descricao("bla bla")
			.status(Status.ABERTO)
			.dataEmissao(LocalDate.now())
			.dataFechamento(null)
			.idCliente(idCliente)
			.idFuncionario(null)
			.build();
	}
	
	@ApiResponses({
		@ApiResponse(code = 201,message = "created",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "Lista as ordens de servicos",notes = "Precisa estar logado como um FUNCIONARIO")
	@GetMapping()
	private List<OrdemServicoResponse> listar() {	
		List<OrdemServicoResponse> ordem = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			ordem.add(buscar(i, 3));
		}
		ordem.add(buscar(3, 5));
		return ordem;
	}
	
	@ApiResponses({
		@ApiResponse(code = 201,message = "created",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "Busca historico de uma ordem de servico",notes = "Precisa estar logado como um CLIENTE ao qual esta ordem de servico pertense ou comum FUNCIONARIO qual quer")
	@GetMapping("/{idOrdemServico}/historico")
	private List<HistoricoResponse> buscarHistorico(
			@ApiParam(value = "id ordem de servico") 
			@PathVariable long idOrdemServico) {
		List<HistoricoResponse> historicos = new ArrayList<>();
		LocalDateTime date = LocalDateTime.now().minusDays(5);
		historicos.add(historico(idOrdemServico,"Aberto",4L,null,date));
		historicos.add(historico(idOrdemServico,"Em Execução",6L,4L,date.plusDays(1)));
		historicos.add(historico(idOrdemServico,"Concluído",7L,4L,date.plusDays(3)));
		
		return historicos;
	}
	
	private HistoricoResponse historico(Long ordemServico,String status, Long id, Long funcionario, LocalDateTime date) {
		return HistoricoResponse.builder()
				.id(id)
				.idFuncionario(funcionario)
				.status(Status.ABERTO)
				.data(date)
				.idOrdemServico(ordemServico)
				.build();
	}
	
	@ApiResponses({
		@ApiResponse(code = 201,message = "created",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "Lista as ordens de servicos do cliente Logado",notes = "Precisa estar logado como um CLIENTE")
	@GetMapping("/cliente")
	private List<OrdemServicoResponse> listarParaCliente() {	
		List<OrdemServicoResponse> ordem = new ArrayList<>();
		ordem.add(buscar(3, 5));
		return ordem;
	}
}
