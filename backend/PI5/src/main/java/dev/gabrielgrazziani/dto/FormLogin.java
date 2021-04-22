package dev.gabrielgrazziani.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FormLogin {
	@ApiModelProperty(notes = "email da pessoa",example = "exemplo@email.com",required = true,position = 0)
	@NotBlank
	private String email;
	@ApiModelProperty(notes = "senha da pessoa",example = "Exemplo$533",required = true,position = 1)
	@NotBlank
	private String senha;
}
