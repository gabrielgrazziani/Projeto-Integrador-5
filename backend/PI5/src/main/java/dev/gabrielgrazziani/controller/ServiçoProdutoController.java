package dev.gabrielgrazziani.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gabrielgrazziani.dto.PessoaResponse;
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
	@ApiOperation(value = "Busca uma servico produto",notes = "Não precisa estar logado")
	@GetMapping("/{id}")
	private ServiçoProdutoResponse buscar(
			@ApiParam(value = "id servico produto") 
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
	@ApiOperation(value = "Listar servicos produtos",notes = "Não precisa estar logado")
	@GetMapping()
	private List<ServiçoProdutoResponse> listar() {	
		List<ServiçoProdutoResponse> serviçoProdutos = new ArrayList<ServiçoProdutoResponse>();
		
		for (int i = 1; i <= 5; i++) {
			serviçoProdutos.add(buscar(i));
		}
		return serviçoProdutos;
	}
}
