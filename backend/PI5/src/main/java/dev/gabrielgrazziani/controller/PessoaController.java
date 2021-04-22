package dev.gabrielgrazziani.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.gabrielgrazziani.dto.FormLogin;
import dev.gabrielgrazziani.dto.PessoaResponse;
import dev.gabrielgrazziani.exceptions.MensException;
import dev.gabrielgrazziani.model.Perfil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("pessoa")
public class PessoaController {
	@ApiResponses({
		@ApiResponse(code = 200,message = "success",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "login pessoa",notes = "serve tanto para CLIENTE como para FUNCIONARIO")
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public PessoaResponse login(@RequestBody @Valid FormLogin form) {
		return PessoaResponse.builder()
			.nome("Renato")
			.email("renato@gmail.com")
			.cpfCnpj("132.882.170-66")
			.telefone("(32)3232-3232")
			.funcao("")
			.perfil(Perfil.CLIENTE)
			.id(2L)
			.build();
	}
}
