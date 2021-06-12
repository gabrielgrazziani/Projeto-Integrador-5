package dev.gabrielgrazziani.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import dev.gabrielgrazziani.anotations.CpfOuCnpj;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "Formulário de criar Funcionario",description = "Formulário para criação de um Funcionario")
@Data
public class FuncionarioFormCreate {
	@ApiModelProperty(notes = "Nome do funcionario",example = "Pedro Ricardo",required = true,position = 0)
	@NotBlank
	private String nome;
	
	@NotBlank
	@ApiModelProperty(notes = "CPF ou CNPJ do funcionario",example = "132.882.170-66",required = true,position = 1)
	@CpfOuCnpj
	private String cpfCnpj;


	@NotBlank
	@ApiModelProperty(notes = "email do funcionario",example = "exemplo@email.com",required = true,position = 2)
	@Size(min = 6, max = 20)
	@Email
	private String email;
	
	@NotBlank
	@ApiModelProperty(notes = "telefone do funcionario",example = "(32)3232-3232",position = 3)
	@Pattern(regexp = "^(\\(\\d{2}\\)\\d{4}\\-\\d{4})|(\\(\\d{2}\\)\\d{5}\\-\\d{4})", message = "Telefone deve ter o formato= (99)9999-9999 ou (99)99999-9999")
	private String telefone;

	@ApiModelProperty(
			notes = "senha do funcionario - deve ter no mínimo 6 caracteres, um digito, um caractere especial e uma letra maiúscula",
			example = "Exemplo$533",
			required = true,
			position = 4)
	@NotBlank
	@Pattern(regexp = "(?=^.{6,}$)((?=.*\\d)(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message = "A senha deve conter 1 letra Maiúscula, números e caracteres especiais e no mínimo 6 caracteres!")
	private String senha;
	
	@ApiModelProperty(notes = "Funçao funcionario",example = "entregador",required = true,position = 5)
	@NotBlank
	private String funcao;
}
