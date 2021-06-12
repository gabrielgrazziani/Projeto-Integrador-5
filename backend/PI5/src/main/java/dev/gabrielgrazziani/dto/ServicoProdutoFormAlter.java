package dev.gabrielgrazziani.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import dev.gabrielgrazziani.model.Tipo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ServicoProdutoFormAlter {

	@NotNull
	@Positive
	private Long id;
	
	@NotBlank
	@Size(min = 6)
	private String nome;
	
	@NotNull
	@ApiModelProperty(example = "PRODUTO")
	private Tipo tipo;
	
	@ApiModelProperty(notes = "O valor que este Serviço/Produto custa para a empresa")
	@Positive
	private BigDecimal valorCusto;
	
	@ApiModelProperty(notes = "O valor que este Serviço/Produto é vendido")
	@Positive
	private BigDecimal valorComercial;
	
	@NotNull
	private String unidadeMedida;
}
