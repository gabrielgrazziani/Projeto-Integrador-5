package dev.gabrielgrazziani.dto;

import java.time.LocalDate;

import dev.gabrielgrazziani.model.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrdemServicoResponse {
	private Long id;
	private Long idFuncionario;
	private Long idCliente;
	private LocalDate dataEmissao;
	private LocalDate dataFechamento;
	private Status status;
	private String descricao;

}
