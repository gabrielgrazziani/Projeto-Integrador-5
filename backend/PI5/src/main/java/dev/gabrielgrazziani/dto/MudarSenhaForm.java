package dev.gabrielgrazziani.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class MudarSenhaForm {
	@Positive
	private Long idPessoa;
	@NotBlank
	private String antigaSenha;
	@NotBlank
	@Pattern(regexp = "(?=^.{6,}$)((?=.*\\d)(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message = "A senha deve conter 1 letra Maiúscula, números e caracteres especiais e no mínimo 6 caracteres!")
	private String novaSenha;
}
