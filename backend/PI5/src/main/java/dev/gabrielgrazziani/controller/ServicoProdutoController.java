package dev.gabrielgrazziani.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import dev.gabrielgrazziani.dto.ServicoProdutoResponse;
import dev.gabrielgrazziani.exceptions.MensException;
import dev.gabrielgrazziani.model.ServicoProduto;
import dev.gabrielgrazziani.service.ServicoProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("servico_produto")
@Api(tags = "servico_produto")
@CrossOrigin(origins = "http://localhost:4200")
public class ServicoProdutoController {

	private final ServicoProdutoService servicoProdutoService;
	
	@ApiResponses({
		@ApiResponse(code = 200,message = "ok",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "Busca uma serviço produto",notes = "Não precisa estar logado")
	@GetMapping("/{id}")
	private ServicoProdutoResponse buscar(
			@ApiParam(value = "id serviço produto") 
			@PathVariable long id
	) {	
		ServicoProduto servicoProduto = servicoProdutoService.busca(id);
		ServicoProdutoResponse servicoProdutoResponse = new ServicoProdutoResponse();
		BeanUtils.copyProperties(servicoProduto, servicoProdutoResponse);
		return servicoProdutoResponse;
	}
	
	@ApiResponses({
		@ApiResponse(code = 200,message = "ok",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "Listar serviços produtos",notes = "Não precisa estar logado")
	@GetMapping()
	private List<ServicoProdutoResponse> listar() {	
		List<ServicoProdutoResponse> servicoProdutos = servicoProdutoService.listar()
			.stream()
			.map(sp -> {
				ServicoProdutoResponse servicoProdutoResponse = new ServicoProdutoResponse();
				BeanUtils.copyProperties(sp, servicoProdutoResponse);
				return servicoProdutoResponse;
			})
			.collect(Collectors.toList());

		return servicoProdutos;
	}
	
	@ApiResponses({
		@ApiResponse(code = 201,message = "created",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "cria um serviço produto",notes = "Precisa estar logado como um FUNCIONARIO")
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	private ServicoProdutoResponse cria(@Valid @RequestBody ServicoProdutoForm form) {	
		ServicoProduto servicoProduto = new ServicoProduto();
		BeanUtils.copyProperties(form, servicoProduto);
		
		servicoProduto = servicoProdutoService.cria(servicoProduto);
		
		ServicoProdutoResponse servicoProdutoResponse = new ServicoProdutoResponse();
		BeanUtils.copyProperties(servicoProduto, servicoProdutoResponse);
		return servicoProdutoResponse;
	}
	
	@ApiResponses({
		@ApiResponse(code = 200,message = "ok",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "altera um serviço produto",notes = "Precisa estar logado como um FUNCIONARIO")
	@PutMapping
	private ResponseEntity<ServicoProdutoResponse> altera(@Valid @RequestBody ServicoProdutoFormAlter form) {			
		ServicoProduto servicoProduto = new ServicoProduto();
		BeanUtils.copyProperties(form, servicoProduto);
		
		if(form.getId() == null) {
			return ResponseEntity.badRequest().build();
		}
		
		servicoProduto = servicoProdutoService.altera(servicoProduto);
		
		ServicoProdutoResponse servicoProdutoResponse = new ServicoProdutoResponse();
		BeanUtils.copyProperties(servicoProduto, servicoProdutoResponse);
		return ResponseEntity.ok(servicoProdutoResponse);
	}
	
	@ApiResponses({
		@ApiResponse(code = 204,message = "No Content",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "exclui um serviço produto",notes = "Precisa estar logado como um FUNCIONARIO")
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	private void exclui(@ApiParam(value = "id serviço produto") @PathVariable Long id) {	
		servicoProdutoService.exclui(id);
	}
}
