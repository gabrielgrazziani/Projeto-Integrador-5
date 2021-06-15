package dev.gabrielgrazziani.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Historico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_historico")
	private Long id;
	@Column(name = "data_historico")
	private LocalDateTime data;
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "ordemservico")
	private OrdemServico ordemServico;
	@ManyToOne
	@JoinColumn(name = "funcionario")
	private Pessoa funcionario;
	
	public Historico() {
	}

	public Historico(OrdemServico ordemServico, Pessoa funcionario, Status status) {
		this.ordemServico = ordemServico;
		this.funcionario = funcionario;
		this.status = status;
		this.data = LocalDateTime.now();
	}
	
}
