package dev.gabrielgrazziani.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ordemservico")
public class OrdemServico {
	@Id
	@GeneratedValue()
	@Column(name = "ordemservico_id")
	private Long id;
	@ManyToOne(optional = true)
	private Pessoa funcionario;
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Pessoa cliente;
	private LocalDate dataEmissao;
	private LocalDate dataFechamento;
	@Enumerated(EnumType.STRING)
	private Status status;
	private String descricao;
}
