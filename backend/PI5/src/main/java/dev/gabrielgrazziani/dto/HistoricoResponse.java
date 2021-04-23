package dev.gabrielgrazziani.dto;

import java.time.LocalDateTime;

import dev.gabrielgrazziani.model.Status;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HistoricoResponse {
	private Long id;
	private Long idOrdemServico;
	private Long idFuncionario;
	@ApiModelProperty(notes = "Data em que este historico foi gerado")
	private LocalDateTime data;
	private Status status;
}
