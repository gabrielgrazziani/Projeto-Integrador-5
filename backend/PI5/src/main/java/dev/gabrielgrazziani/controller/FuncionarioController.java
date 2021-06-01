package dev.gabrielgrazziani.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import dev.gabrielgrazziani.dto.FuncionarioFormAlterar;
import dev.gabrielgrazziani.dto.FuncionarioFormCreate;
import dev.gabrielgrazziani.dto.MudarSenhaForm;
import dev.gabrielgrazziani.dto.PessoaResponse;
import dev.gabrielgrazziani.exceptions.MensException;
import dev.gabrielgrazziani.model.Perfil;
import dev.gabrielgrazziani.model.Pessoa;
import dev.gabrielgrazziani.service.FuncionarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("funcionario")
@Api(tags = "funcionario")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@ApiResponses({
		@ApiResponse(code = 200,message = "ok",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "Altera o funcionario",notes = "Precisa estar logado como um FUNCIONARIO")
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/{id}")
	public PessoaResponse alterar(@PathVariable Long id,@Valid @RequestBody FuncionarioFormAlterar form) {
		Pessoa pessoa = new Pessoa();
		BeanUtils.copyProperties(form, pessoa);
		pessoa.setId(id);
		pessoa.setPerfil(Perfil.FUNCIONARIO);
		
		funcionarioService.altera(pessoa);
		
		PessoaResponse pessoaResponse = new PessoaResponse();
		BeanUtils.copyProperties(pessoa, pessoaResponse);
		
		return pessoaResponse;
	}

	@ApiResponses({
		@ApiResponse(code = 201,message = "Created",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(
			value = "Cria funcionario",
			notes = "Precisa estar logado como um FUNCIONARIO")
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public ResponseEntity<PessoaResponse> cria(@Valid @RequestBody FuncionarioFormCreate formCreate) {
		Pessoa pessoa = new Pessoa();
		BeanUtils.copyProperties(formCreate, pessoa);
		pessoa.setPerfil(Perfil.FUNCIONARIO);
		pessoa.setLogin(pessoa.getEmail());
		
		funcionarioService.cria(pessoa);
		
		PessoaResponse pessoaResponse = new PessoaResponse();
		BeanUtils.copyProperties(pessoa, pessoaResponse);
		
		return ResponseEntity.ok(pessoaResponse);
	}
	
	@ApiResponses({
		@ApiResponse(code = 204,message = "NO CONTENT",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "Mudar senha funcionario",notes = "Precisa estar logado como um FUNCIONARIO")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PatchMapping("senha")
	public ResponseEntity<?> mudarSenha(@Valid @RequestBody MudarSenhaForm form) {
		if(funcionarioService.mudarSenha(form)) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	
	@ApiResponses({
		@ApiResponse(code = 200,message = "ok",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "Buscar funcionario",notes = "Precisa estar logado como um FUNCIONARIO")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/{id}")
	public PessoaResponse buscar(@PathVariable Long id) {
		Pessoa pessoa = funcionarioService.busca(id);
		
		PessoaResponse pessoaResponse = new PessoaResponse();
		BeanUtils.copyProperties(pessoa, pessoaResponse);
		
		return pessoaResponse;
	}
	
	@ApiResponses({
		@ApiResponse(code = 200,message = "ok",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "Lista funcionarios",notes = "Precisa estar logado como um FUNCIONARIO")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping
	public List<PessoaResponse> listar() {
		List<Pessoa> lista = funcionarioService.lista();
		List<PessoaResponse> listaDto = new ArrayList<>();
		
		for(Pessoa pessoa: lista) {
			PessoaResponse pessoaResponse = new PessoaResponse();
			BeanUtils.copyProperties(pessoa, pessoaResponse);
			listaDto.add(pessoaResponse);
		}
		
		return listaDto;
	}
	
	@ApiResponses({
		@ApiResponse(code = 200,message = "ok",response = PessoaResponse.class),
		@ApiResponse(code = 400,message = "Input Invalido",response = MensException.class)
	})
	@ApiOperation(value = "Excluir funcionario",notes = "Precisa estar logado como um FUNCIONARIO")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		funcionarioService.excluir(id);
	}
	
	
}
