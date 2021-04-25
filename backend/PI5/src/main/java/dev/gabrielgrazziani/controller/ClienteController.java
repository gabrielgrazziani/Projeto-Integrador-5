package dev.gabrielgrazziani.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.gabrielgrazziani.dto.ClienteFormCreate;
import dev.gabrielgrazziani.dto.PessoaResponse;
import dev.gabrielgrazziani.exceptions.MensException;
import dev.gabrielgrazziani.model.Perfil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("cliente")
@Api(tags = "cliente")
public class ClienteController {

	@ApiResponses({
		@ApiResponse(code = 201,message = "created",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "Cria um Cliente",notes = "Não precisa estar logado para usar este endpoint")
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public PessoaResponse create(@RequestBody @Valid ClienteFormCreate form) {
		return PessoaResponse.builder()
			.nome(form.getNome())
			.email(form.getEmail())
			.cpfCnpj(form.getCpfCnpj())
			.telefone(form.getTelefone())
			.id(2L)
			.perfil(Perfil.CLIENTE)
			.funcao("")
			.build();
	}
}
