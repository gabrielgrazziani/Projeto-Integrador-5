package dev.gabrielgrazziani.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemForm {
	@NotNull
	@Positive
	private Long idServicoProduto;
	@NotNull
	@Positive
	private float quantidade;
}
