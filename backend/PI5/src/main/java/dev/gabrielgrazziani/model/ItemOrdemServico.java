package dev.gabrielgrazziani.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "itensordemservico")
public class ItemOrdemServico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_itensordemservico")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "ordemservico")
	private OrdemServico ordemServico;
	@ManyToOne
	@JoinColumn(name = "servicoprodurto")
	private ServicoProduto servicoProduto;
	private int quantidade;
	@Column(name = "valorunit")
	private BigDecimal valorUnidade;
}
