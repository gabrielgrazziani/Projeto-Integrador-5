package dev.gabrielgrazziani.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class ItemOrdemServico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "ordem_servico_id")
	private OrdemServico ordemServico;
	@ManyToOne
	@JoinColumn(name = "servico_produto_id")
	private ServicoProduto servicoProduto;
	private int quantidade;
	private BigDecimal valorUnidade;
}
