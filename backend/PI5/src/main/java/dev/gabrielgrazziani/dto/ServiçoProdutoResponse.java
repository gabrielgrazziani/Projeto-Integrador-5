package dev.gabrielgrazziani.dto;

import java.math.BigDecimal;

import dev.gabrielgrazziani.model.Tipo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiçoProdutoResponse {
	private Long id;
	private String nome;
	private Tipo tipo;
	private BigDecimal valorCusto;
	private BigDecimal valorComercial;
	private String unidadeMedida;
}
