package dev.gabrielgrazziani.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemResponse {
	private Long id;
	private Long idOrdemServico;
	private Long idServicoProduto;
	private float quantidade;
	private BigDecimal valorUnidade;
}
