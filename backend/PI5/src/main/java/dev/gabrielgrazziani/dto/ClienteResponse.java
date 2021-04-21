package dev.gabrielgrazziani.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteResponse {
	
	@ApiModelProperty(notes = "Id do cliente",example = "Pedro Ricardo",required = true,position = 0)
	private Long id;
	
	@ApiModelProperty(notes = "Nome do cliente",example = "Pedro Ricardo",required = true,position = 1)
	private String nome;
	
	@ApiModelProperty(notes = "CPF ou CNPJ do cliente",example = "132.882.170-66 ou 38.297.297/0001-71",required = true,position = 2)
	private String cpfCnpj;

	@ApiModelProperty(notes = "email do cliente",example = "exemplo@email.com",required = true,position = 3)
	private String email;
	
	@ApiModelProperty(notes = "telefone do cliente",example = "(32)3232-3232",position = 4)
	private String telefone;
}
