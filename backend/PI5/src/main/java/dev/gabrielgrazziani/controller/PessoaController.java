package dev.gabrielgrazziani.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.gabrielgrazziani.dto.PessoaResponse;
import dev.gabrielgrazziani.exceptions.MensException;
import dev.gabrielgrazziani.model.Perfil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("pessoa")
@Api(tags = "pessoa")
public class PessoaController {

	@ApiResponses({
		@ApiResponse(code = 200,message = "ok",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "Mostra a pessoa logada",notes = "Precisa estar logado para usar este endpoint")
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public PessoaResponse pessoa() {
		return PessoaResponse.builder()
			.nome("fulano")
			.email("fulano@gmail.com")
			.cpfCnpj("121.122.122-32")
			.telefone("(21)3221-1212")
			.id(2L)
			.perfil(Perfil.CLIENTE)
			.funcao("")
			.build();
	}
}
