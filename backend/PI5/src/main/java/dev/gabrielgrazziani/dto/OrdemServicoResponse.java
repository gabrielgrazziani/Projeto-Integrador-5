package dev.gabrielgrazziani.dto;

import java.time.LocalDate;

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
	private String status;
	private String descricao;

}
