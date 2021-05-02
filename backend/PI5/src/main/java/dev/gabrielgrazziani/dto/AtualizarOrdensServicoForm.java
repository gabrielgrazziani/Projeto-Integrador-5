package dev.gabrielgrazziani.dto;

import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

import dev.gabrielgrazziani.model.Status;
import lombok.Data;

@Data
public class AtualizarOrdensServicoForm {
	@Positive
	@NotNull
	private Long idOrdensServico;
	@NotNull
	private Status status;
	@Positive
	private Long idFuncionario;
}
