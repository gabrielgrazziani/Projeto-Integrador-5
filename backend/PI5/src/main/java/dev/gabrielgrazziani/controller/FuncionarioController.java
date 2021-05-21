package dev.gabrielgrazziani.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.gabrielgrazziani.dto.FuncionarioFormCreate;
import dev.gabrielgrazziani.dto.MudarSenhaForm;
import dev.gabrielgrazziani.dto.PessoaResponse;
import dev.gabrielgrazziani.exceptions.MensException;
import dev.gabrielgrazziani.model.Perfil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("funcionario")
@Api(tags = "funcionario")
public class FuncionarioController {

	@ApiResponses({
		@ApiResponse(code = 201,message = "Created",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "Cria funcionario",notes = "Precisa estar logado como um FUNCIONARIO")
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public PessoaResponse cria(@Valid @RequestBody FuncionarioFormCreate form) {
		return PessoaResponse.builder()
			.nome(form.getNome())
			.email(form.getEmail())
			.cpfCnpj(form.getCpfCnpj())
			.telefone(form.getTelefone())
			.id(2L)
			.perfil(Perfil.FUNCIONARIO)
			.funcao(form.getFuncao())
			.build();
	}
	
	@ApiResponses({
		@ApiResponse(code = 200,message = "ok",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "Altera o funcionario",notes = "Precisa estar logado como um FUNCIONARIO")
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping
	public PessoaResponse alterar(@Valid @RequestBody FuncionarioFormCreate form) {
		return PessoaResponse.builder()
			.nome(form.getNome())
			.email(form.getEmail())
			.cpfCnpj(form.getCpfCnpj())
			.telefone(form.getTelefone())
			.id(2L)
			.perfil(Perfil.FUNCIONARIO)
			.funcao(form.getFuncao())
			.build();
	}
	
	@ApiResponses({
		@ApiResponse(code = 204,message = "NO CONTENT",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "Mudar senha funcionario",notes = "Precisa estar logado como um FUNCIONARIO")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PatchMapping("senha")
	public void nudarSenha(@Valid @RequestBody MudarSenhaForm form) {
		
	}
	
	
	@ApiResponses({
		@ApiResponse(code = 200,message = "ok",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "Buscar funcionario",notes = "Precisa estar logado como um FUNCIONARIO")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/{id}")
	public PessoaResponse buscar(@PathVariable Long id) {
		return PessoaResponse.builder()
			.nome("fulano")
			.email("fulano@gmail.com")
			.cpfCnpj("132.882.170-66")
			.telefone("(32)3232-3232")
			.id(2L)
			.perfil(Perfil.FUNCIONARIO)
			.funcao("entregador")
			.build();
	}
	
	@ApiResponses({
		@ApiResponse(code = 200,message = "ok",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "Lista funcionarios",notes = "Precisa estar logado como um FUNCIONARIO")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping
	public List<PessoaResponse> listar() {
		List<PessoaResponse> lista = new ArrayList<>();
		PessoaResponse p1 = PessoaResponse.builder()
			.nome("fulano")
			.email("fulano@gmail.com")
			.cpfCnpj("132.882.170-66")
			.telefone("(32)3232-3232")
			.id(2L)
			.perfil(Perfil.FUNCIONARIO)
			.funcao("entregador")
			.build();
		PessoaResponse p2 = PessoaResponse.builder()
				.nome("fulano2")
				.email("fulano2@gmail.com")
				.cpfCnpj("132.882.170-66")
				.telefone("(32)3232-3232")
				.id(3L)
				.perfil(Perfil.FUNCIONARIO)
				.funcao("entregador")
				.build();
		lista.add(p1);
		lista.add(p2);
		return lista;
	}
	
	@ApiResponses({
		@ApiResponse(code = 200,message = "ok",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "Excluir funcionario",notes = "Precisa estar logado como um FUNCIONARIO")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void bucar(@PathVariable Long id) {
		
	}
	
	
}
