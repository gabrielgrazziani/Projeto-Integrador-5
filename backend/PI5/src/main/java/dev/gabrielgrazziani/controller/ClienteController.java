package dev.gabrielgrazziani.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.gabrielgrazziani.dto.ClienteFormCreate;
import dev.gabrielgrazziani.dto.ClienteResponse;
import dev.gabrielgrazziani.exceptions.MensException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("cliente")
public class ClienteController {

	@ApiResponses({
		@ApiResponse(code = 201,message = "created",response = ClienteResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "Cria um Cliente")
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ClienteResponse create(@RequestBody @Valid ClienteFormCreate form) {
		return ClienteResponse.builder()
			.nome(form.getNome())
			.email(form.getEmail())
			.cpfCnpj(form.getCpfCnpj())
			.telefone(form.getTelefone())
			.id(2L)
			.build();
	}
}
