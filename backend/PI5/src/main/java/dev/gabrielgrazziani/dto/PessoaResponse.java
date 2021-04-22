package dev.gabrielgrazziani.dto;

import dev.gabrielgrazziani.model.Perfil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(value = "Pessoa Response")
public class PessoaResponse {
	
	@ApiModelProperty(notes = "Id da pessoa",example = "Pedro Ricardo",required = true,position = 0)
	private Long id;
	
	@ApiModelProperty(notes = "Nome da pessoa",example = "Pedro Ricardo",required = true,position = 1)
	private String nome;
	
	@ApiModelProperty(notes = "CPF ou CNPJ da pessoa",example = "132.882.170-66",required = true,position = 2)
	private String cpfCnpj;

	@ApiModelProperty(notes = "email da pessoa",example = "exemplo@email.com",required = true,position = 3)
	private String email;
	
	@ApiModelProperty(notes = "telefone da pessoa",example = "(32)3232-3232",position = 4)
	private String telefone;
	
	@ApiModelProperty(notes = "perfil da pessoa",example = "CLIENTE",position = 5)
	private Perfil perfil;
	
	@ApiModelProperty(notes = "funcao da pessoa, caso sejá um funcionario",position = 6)
	private String funcao;
}
