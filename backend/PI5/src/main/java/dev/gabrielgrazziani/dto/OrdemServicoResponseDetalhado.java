package dev.gabrielgrazziani.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import dev.gabrielgrazziani.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdemServicoResponseDetalhado {
	private Long id;
	private PessoaResponse funcionario;
	private PessoaResponse cliente;
	private LocalDate dataEmissao;
	private LocalDate dataFechamento;
	private Status status;
	private String descricao;
	private List<ItemResponse> items;
	
	public BigDecimal getTotal() {
		BigDecimal soma = BigDecimal.ZERO;
		for(ItemResponse item : items) {
			soma.add(item.getSoma());
		}
		return soma;
	}

}
