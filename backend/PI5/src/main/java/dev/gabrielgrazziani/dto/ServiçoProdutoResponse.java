package dev.gabrielgrazziani.dto;

import java.math.BigDecimal;

import dev.gabrielgrazziani.model.Tipo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiçoProdutoResponse {
	private Long id;
	private String nome;
	private Tipo tipo;
	@ApiModelProperty(notes = "O valor que este Serviço/Produto custa para a empresa")
	private BigDecimal valorCusto;
	@ApiModelProperty(notes = "O valor que este Serviço/Produto e vendido")
	private BigDecimal valorComercial;
	private String unidadeMedida;
}
