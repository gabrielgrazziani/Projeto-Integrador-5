package dev.gabrielgrazziani.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HistoricoResponse {
	private Long id;
	private Long idOrdemServico;
	private Long idFuncionario;
	private LocalDateTime data;
	private String status;
}
