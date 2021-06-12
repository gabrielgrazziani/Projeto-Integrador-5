package dev.gabrielgrazziani.dto;

import java.time.LocalDate;

import dev.gabrielgrazziani.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdemServicoResponse {
	private Long id;
	private PessoaResponse funcionario;
	private PessoaResponse cliente;
	private LocalDate dataEmissao;
	private LocalDate dataFechamento;
	private Status status;
	private String descricao;

}
