package dev.gabrielgrazziani.dto;

import java.math.BigDecimal;

import dev.gabrielgrazziani.model.Tipo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicoProdutoResponse {
	private Long id;
	private String nome;
	private Tipo tipo;
	@ApiModelProperty(notes = "O valor que este Serviço/Produto custa para a empresa")
	private BigDecimal valorCusto;
	@ApiModelProperty(notes = "O valor que este Serviço/Produto e vendido")
	private BigDecimal valorComercial;
	private String unidadeMedida;
}
