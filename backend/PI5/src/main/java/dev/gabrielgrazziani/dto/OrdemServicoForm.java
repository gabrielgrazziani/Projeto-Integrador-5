package dev.gabrielgrazziani.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrdemServicoForm {
	@NotNull
	private String descricao;
	@Size(min = 1,message = "Deve ter ao menos um item")
	@Valid
	private List<ItemForm> items;
}
