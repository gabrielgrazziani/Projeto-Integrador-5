package dev.gabrielgrazziani.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemResponse {
	private Long id;
	private Long idOrdemServico;
	private Long idServicoProduto;
	private int quantidade;
	@ApiModelProperty(notes = "Valor de uma unidade")
	private BigDecimal valorUnidade;
}
