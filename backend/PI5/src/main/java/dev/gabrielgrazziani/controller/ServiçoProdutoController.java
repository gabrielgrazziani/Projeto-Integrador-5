package dev.gabrielgrazziani.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.gabrielgrazziani.dto.PessoaResponse;
import dev.gabrielgrazziani.dto.ServicoProdutoForm;
import dev.gabrielgrazziani.dto.ServicoProdutoFormAlter;
import dev.gabrielgrazziani.dto.ServiçoProdutoResponse;
import dev.gabrielgrazziani.exceptions.MensException;
import dev.gabrielgrazziani.model.Tipo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("servico_produto")
@Api(tags = "servico_produto")
public class ServiçoProdutoController {

	@ApiResponses({
		@ApiResponse(code = 200,message = "ok",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "Busca uma serviço produto",notes = "Não precisa estar logado")
	@GetMapping("/{id}")
	private ServiçoProdutoResponse buscar(
			@ApiParam(value = "id serviço produto") 
			@PathVariable long id
	) {	
		return ServiçoProdutoResponse.builder()
			.id(id)
			.nome("chaveiro "+ id)
			.tipo(Tipo.PRODUTO)
			.valorComercial(new BigDecimal(id * 1.4))
			.valorCusto(new BigDecimal(id))
			.unidadeMedida("20 Kg")
			.build();
	}
	
	@ApiResponses({
		@ApiResponse(code = 200,message = "ok",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "Listar serviços produtos",notes = "Não precisa estar logado")
	@GetMapping()
	private List<ServiçoProdutoResponse> listar() {	
		List<ServiçoProdutoResponse> serviçoProdutos = new ArrayList<ServiçoProdutoResponse>();
		
		for (int i = 1; i <= 5; i++) {
			serviçoProdutos.add(buscar(i));
		}
		return serviçoProdutos;
	}
	
	@ApiResponses({
		@ApiResponse(code = 201,message = "created",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "cria um serviço produto",notes = "Precisa estar logado como um FUNCIONARIO")
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	private ServiçoProdutoResponse cria(@Valid @RequestBody ServicoProdutoForm form) {	
		
		return ServiçoProdutoResponse.builder()
				.id(4L)
				.nome(form.getNome())
				.tipo(form.getTipo())
				.valorComercial(form.getValorComercial())
				.valorCusto(form.getValorCusto())
				.unidadeMedida(form.getUnidadeMedida())
				.build();
	}
	
	@ApiResponses({
		@ApiResponse(code = 200,message = "ok",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "altera um serviço produto",notes = "Precisa estar logado como um FUNCIONARIO")
	@PutMapping
	private ServiçoProdutoResponse altera(@Valid @RequestBody ServicoProdutoFormAlter form) {	
		
		return ServiçoProdutoResponse.builder()
				.id(form.getId())
				.nome(form.getNome())
				.tipo(form.getTipo())
				.valorComercial(form.getValorComercial())
				.valorCusto(form.getValorCusto())
				.unidadeMedida(form.getUnidadeMedida())
				.build();
	}
	
	@ApiResponses({
		@ApiResponse(code = 204,message = "No Content",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "exclui um serviço produto",notes = "Precisa estar logado como um FUNCIONARIO")
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	private void exclui(@ApiParam(value = "id serviço produto") @PathVariable Long id) {	
		
	}
}
